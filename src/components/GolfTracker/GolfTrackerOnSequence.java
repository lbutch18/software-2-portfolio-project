package components.GolfTracker;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Component for tracking of golf rounds. Each round is assigned to a course,
 * with the tracker represented as a Sequence of Rounds. Allows for simple
 * handicap calculation, stat tracking, etc.
 *
 * @convention Sequence of Rounds sorted by date
 * @correspondence Each entry in the Sequence represents one round played
 *
 * @author Luke Butcher
 */
public final class GolfTrackerOnSequence extends GolfTrackerSecondary {

    /** Private representation. */
    private Sequence<Round> roundEntries;

    public static class Round {

        /*
         * A Round consists of "strokes" shot at "course" where "holesPlayed"
         * holes are played on date "month"/"day"/"year" at "course". id handles
         * when multiple rounds played on same day and course (0 = first, 1 =
         * second)
         */
        private int strokes, holesPlayed, month, day, year, id;
        private double differential;
        private Course course;

        /* Numerator for slope's contribution to the differential calculation */
        private static final double SLOPE_CONSTANT = 113.0;

        public Round(int strokes, int holesPlayed, int month, int day, int year,
                int id, Course course) {
            this.strokes = strokes;
            this.holesPlayed = holesPlayed;
            this.month = month;
            this.day = day;
            this.year = year;
            this.id = id;
            this.course = course;

            this.differential = calculateDifferential(strokes, course.rating(),
                    course.slope());
        }

        /*
         * Helper to calculate handicap differential
         */
        private static double calculateDifferential(int score, double rating,
                double slope) {
            return Math
                    .round(((SLOPE_CONSTANT / slope) * (score - rating)) * 10.0)
                    / 10.0;
        }

        /* Get the score for this */
        public int getScore() {
            return this.strokes;
        }

        /* Get the number of holes played for this */
        public int getHolesPlayed() {
            return this.holesPlayed;
        }

        /* Get the date this was played as the array [M, D, Y] */
        public int[] getDate() {
            int[] date = { this.month, this.day, this.year };
            return date;
        }

        /* Get which round in the day this is */
        public int getID() {
            return this.id;
        }

        public double getDiff() {
            return this.differential;
        }

        /* Get the Course this was played at */
        public Course getCourse() {
            return this.course;
        }

        @Override
        public String toString() {
            return "Round data:" + "\nStrokes: " + this.strokes
                    + "\nHoles Played: " + this.holesPlayed + "\nDate: "
                    + this.month + "/" + this.day + "/" + this.year + "\nID: "
                    + this.id + "\nCourse: " + this.course.toString();
        }

        @Override
        public int hashCode() {
            return this.strokes + this.holesPlayed + this.month + this.day
                    + this.year + this.id + this.course.hashCode();
        }

        @Override
        public boolean equals(Object compared) {
            if (compared == null) {
                return false;
            }
            if (this == compared) {
                return true;
            }
            if (!(compared instanceof Round)) {
                return false;
            }

            Round casted = (Round) compared;
            return this.strokes == casted.strokes
                    && this.holesPlayed == casted.holesPlayed
                    && this.month == casted.month && this.day == casted.day
                    && this.year == casted.year && this.id == casted.id
                    && this.course.equals(casted.course)
                    && this.differential == casted.differential;
        }
    }

    /**
     * A Course has name "name" and is rated "rating" with a slope rating of
     * "slope".
     */
    public record Course(String name, double rating, double slope) {
    }

    /**
     * No-argument constructor.
     */
    public GolfTrackerOnSequence() {
        this.createNewRep();
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.roundEntries = new Sequence1L<>();
    }

    @Override
    public void addRound(int strokes, int holesPlayed, int month, int day,
            int year, int id, Course course) {

        int count = 0;
        if (this.roundEntries.length() > 0) {
            int[] newDate = { month, day, year };
            while (count < this.roundEntries.length() && compareRoundDate(
                    this.roundEntries.entry(count).getDate(), newDate,
                    this.roundEntries.entry(count).getID(), id) > 0) {
                count++;
            }
        }
        this.roundEntries.add(count,
                new Round(strokes, holesPlayed, month, day, year, id, course));
    }

    /**
     * Helper method to compare dates.
     *
     */
    static private int compareRoundDate(int[] date1, int[] date2, int id1,
            int id2) {
        int result = 0;
        if (date2[2] - date1[2] != 0) { // year
            result = date2[2] - date1[2];
        } else if (date2[0] - date1[0] != 0) { // month
            result = date2[0] - date1[0];
        } else if (date2[1] - date1[1] != 0) {
            result = date2[1] - date1[1];
        } else {
            result = id2 - id1;
        }

        return result;

    }

    @Override
    public void deleteRound(Round round) {
        int low = 0;
        int high = this.roundEntries.length() - 1;
        boolean found = false;
        int entry = this.roundEntries.length() / 2;
        while (!found) {
            Round currentRound = this.roundEntries.entry(entry);
            int compareResult = compareRoundDate(currentRound.getDate(),
                    round.getDate(), currentRound.getID(), round.getID());

            if (compareResult > 0) {
                low = entry + 1;
            } else if (compareResult < 0) {
                high = entry - 1;
            } else {
                found = true;
            }
            entry = (low + high) / 2;
        }

        this.roundEntries.remove(entry);
    }

    @Override
    public Sequence<Round> getAllRounds() {
        return this.roundEntries;
    }

    @Override
    public int numRounds() {
        return this.roundEntries.length();
    }

    @Override
    public boolean contains(Round round) {
        boolean found = false;
        int entry = 0;
        while (entry < this.roundEntries.length() && !found) {
            found = this.roundEntries.entry(entry).equals(round);
            entry++;
        }
        return found;
    }

    @Override
    public GolfTracker newInstance() {
        try {
            return (GolfTracker) this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public void transferFrom(GolfTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof GolfTrackerOnSequence : ""
                + "Violation of: source is of dynamic type GolfTrackerOnSequence";

        GolfTrackerOnSequence localSource = (GolfTrackerOnSequence) source;
        this.roundEntries = localSource.roundEntries;
        localSource.createNewRep();
    }

}
