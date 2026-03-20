package components.GolfTracker;

import java.util.Arrays;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/*
* Allows for tracking of rounds played at courses, calculates handicaps (simply
* for now)
*/
public class GolfTracker1 {

    /*
     * GolfTracker is represented as a Sequence of Rounds ("roundEntries")
     * ordered with the most recent (by date, then ID) rounds at the end of the
     * Sequence
     */
    private Sequence<GolfTracker1.Round> roundEntries;

    /* initialize this.roundEntries to be empty */
    public GolfTracker1() {
        this.roundEntries = new Sequence1L<>();
    }

    /* Adds a round to the tracker */
    public void addRound(int strokes, int holesPlayed, int month, int day,
            int year, int id, GolfTracker1.Course course) {

        int count = 0;
        if (this.roundEntries.length() > 0) {
            int[] newDate = { month, day, year };
            while (count < this.roundEntries.length() && this.compareRoundDate(
                    this.roundEntries.entry(count).getDate(), newDate,
                    this.roundEntries.entry(count).getID(), id) < 0) {
                count++;
            }
        }
        this.roundEntries.add(count,
                new Round(strokes, holesPlayed, month, day, year, id, course));
    }

    /*
     * Helper method to compare dates -- assume dates are [M, D, Y] Should I do
     * a custom comparator and use SortingMachine instead?
     */
    private int compareRoundDate(int[] date1, int[] date2, int id1, int id2) {
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

    /*
     * Remove a round from the tracker with PRECONDITION: round is in
     * this.roundEntries
     */
    public void deleteRound(GolfTracker1.Round round) {
        // Could implement with binary search eventually b/c of ordering
        int count = 0;
        while (!this.roundEntries.entry(count).equals(round)) {
            count++;
        }

        this.roundEntries.remove(count);
    }

    /* Get the roundEntries Set of all Rounds in the tracker */
    public Sequence<GolfTracker1.Round> getAllRounds() {
        return this.roundEntries;
    }

    public int numRounds() {
        return this.roundEntries.length();
    }

    /*
     * Use USGA handicap calculation -- PRECONDITION: At least 3 rounds in
     * this.roundEntries
     */
    public double calculateHandicap() {
        double handicap = 0;
        if (this.numRounds() >= 20) {
            handicap = this.averageLowestXOfNDiffs(8, 20);
        } else if (this.numRounds() == 19) {
            handicap = this.averageLowestXOfNDiffs(7, this.numRounds());
        } else if (this.numRounds() >= 18) {
            handicap = this.averageLowestXOfNDiffs(6, this.numRounds());
        } else if (this.numRounds() >= 15) {
            handicap = this.averageLowestXOfNDiffs(5, this.numRounds());
        } else if (this.numRounds() >= 12) {
            handicap = this.averageLowestXOfNDiffs(4, this.numRounds());
        } else if (this.numRounds() >= 9) {
            handicap = this.averageLowestXOfNDiffs(3, this.numRounds());
        } else if (this.numRounds() >= 7) {
            handicap = this.averageLowestXOfNDiffs(2, this.numRounds());
        } else if (this.numRounds() == 6) {
            handicap = this.averageLowestXOfNDiffs(2, this.numRounds()) - 1.0;
        } else if (this.numRounds() == 5) {
            handicap = this.averageLowestXOfNDiffs(1, this.numRounds());
        } else if (this.numRounds() == 4) {
            handicap = this.averageLowestXOfNDiffs(1, this.numRounds()) - 1.0;
        } else if (this.numRounds() == 3) {
            handicap = this.averageLowestXOfNDiffs(1, this.numRounds()) - 2.0;
        }

        return handicap;
    }

    // Helper for handicap calc -- avg lowest x diffs of last n rounds
    // Handicaps always rounded to nearest tenth
    private double averageLowestXOfNDiffs(int x, int n) {
        double[] diffs = new double[n];

        int count = 0;
        for (int i = this.roundEntries.length() - 1; i >= this.roundEntries
                .length() - n; i--) {
            diffs[count] = this.roundEntries.entry(i).getDiff();
            count++;
        }

        Arrays.sort(diffs);
        double sum = 0;
        for (int i = 0; i < x; i++) {
            sum += diffs[i];
        }

        double handicap = sum / x;
        return Math.round(handicap * 10.0) / 10.0;
    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        GolfTracker1 tracker = new GolfTracker1();

        out.println(
                "Add a round? Press ENTER to continue or type anything else to quit.");
        while (in.nextLine().equals("")) {
            out.print("Round year: ");
            int year = in.nextInteger();
            out.print("Round month: ");
            int month = in.nextInteger();
            out.print("Round day: ");
            int day = in.nextInteger();
            out.print("Course name: ");
            String courseName = in.nextLine();
            out.print("Score: ");
            int score = in.nextInteger();
            out.print("Holes played (9 or 18): ");
            int holesPlayed = in.nextInteger();
            out.print("Course rating: ");
            double rating = in.nextDouble();
            out.print("Course slope: ");
            double slope = in.nextDouble();

            GolfTracker1.Course nextCourse = new GolfTracker1.Course(courseName,
                    rating, slope);
            tracker.addRound(score, holesPlayed, month, day, year, 0,
                    nextCourse); // Assume no rounds on same day for the POC
            out.println(
                    "Add another round? Press ENTER to continue or type anything else to quit.");
        }

        out.println("Rounds added: " + tracker.getAllRounds());
        out.println("Still need to override toString");
        if (tracker.numRounds() >= 3) {
            out.println("Your handicap: " + tracker.calculateHandicap());
        }

        out.close();
        in.close();
    }

    public class Round {

        /*
         * A Round consists of "strokes" shot at "course" where "holesPlayed"
         * holes are played on date "month"/"day"/"year" at "course". id handles
         * when multiple rounds played on same day and course (0 = first, 1 =
         * second)
         */
        private int strokes, holesPlayed, month, day, year, id;
        private double differential;
        private GolfTracker1.Course course;

        /* Numerator for slope's contribution to the differential calculation */
        public static final double SLOPE_CONSTANT = 113.0;

        public Round(int strokes, int holesPlayed, int month, int day, int year,
                int id, GolfTracker1.Course course) {
            this.strokes = strokes;
            this.holesPlayed = holesPlayed;
            this.month = month;
            this.day = day;
            this.year = year;
            this.id = id;
            this.course = course;

            this.differential = calculateDifferential(strokes,
                    course.getRating(), course.getSlope());
        }

        /*
         * Helper to calculate handicap differential (diffs are always rounded
         * to tenths place) -- should this be public or private?
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
        public GolfTracker1.Course getCourse() {
            return this.course;
        }
    }

    static public class Course {

        /*
         * A Course has name "name" and is rated "rating" with a slope rating of
         * "slope"
         */
        private double rating, slope;
        private String name;

        public Course(String name, double rating, double slope) {
            this.name = name;
            this.rating = rating;
            this.slope = slope;
        }

        /* Get the course rating of this */
        public double getRating() {
            return this.rating;
        }

        /* Get the slope rating of this */
        public double getSlope() {
            return this.slope;
        }

        /* Get the name of this */
        public String getName() {
            return this.name;
        }

    }

}
