package components.GolfTracker;

import java.util.Arrays;

import components.GolfTracker.GolfTrackerOnSequence.Course;
import components.GolfTracker.GolfTrackerOnSequence.Round;
import components.sequence.Sequence;

/**
 * Provides enhanced methods on top of the kernel methods for GolfTracker.
 */
public abstract class GolfTrackerSecondary implements GolfTracker {

    /**
     * Helper for handicap calculation. Averages lowest x diffs of last n
     * rounds.
     *
     * @param x
     *            number of lowest diffs to find
     * @param n
     *            number of diffs to find lowest diffs from
     * @return the lowest x of n diffs
     */
    private double averageLowestXOfNDiffs(int x, int n) {
        double[] diffs = new double[n];

        int count = 0;
        Sequence<Round> allRounds = this.getAllRounds();
        for (int i = allRounds.length() - 1; i >= allRounds.length() - n; i--) {
            diffs[count] = allRounds.entry(i).getDiff();
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

    @Override
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

    @Override
    public Round bestRound() {
        Sequence<Round> allRounds = this.getAllRounds();
        Round best = allRounds.entry(0);
        for (int i = 1; i < allRounds.length(); i++) {
            if (allRounds.entry(i).getDiff() < best.getDiff()) {
                best = allRounds.entry(i);
            }
        }

        return best;
    }

    @Override
    public double averageDiff() {
        Sequence<Round> allRounds = this.getAllRounds();
        double average = 0;
        for (int i = 0; i < allRounds.length(); i++) {
            average += allRounds.entry(i).getDiff();
        }

        return average;
    }

    @Override
    public double averageScore(int holesPlayed) {
        Sequence<Round> allRounds = this.getAllRounds();
        double average = 0;

        for (int i = 0; i < allRounds.length(); i++) {
            if (allRounds.entry(i).getHolesPlayed() == holesPlayed) {
                average += allRounds.entry(i).getScore();
            }
        }

        return average;
    }

    @Override
    public Sequence<Round> roundsAtCourse(Course course) {
        Sequence<Round> rounds = this.getAllRounds();

        for (int i = 0; i < rounds.length(); i++) {
            if (!rounds.entry(i).getCourse().equals(course)) {
                rounds.remove(i);
            }
        }

        return rounds;
    }

    @Override
    public void replaceRound(Round oldRound, Round newRound) {
        this.deleteRound(oldRound);
        this.addRound(newRound.getScore(), newRound.getHolesPlayed(),
                newRound.getDate()[0], newRound.getDate()[1],
                newRound.getDate()[2], newRound.getID(), newRound.getCourse());
    }

    @Override
    public String toString() {
        Sequence<Round> allRounds = this.getAllRounds();
        return allRounds.toString();
    }

    @Override
    public int hashCode() {
        Sequence<Round> allRounds = this.getAllRounds();
        return allRounds.hashCode();
    }

    @Override
    public boolean equals(Object compared) {
        Sequence<Round> thisTrackerRounds = this.getAllRounds();
        if (compared instanceof GolfTracker) {
            Sequence<Round> comparedTrackerRounds = ((GolfTracker) compared)
                    .getAllRounds();
            return thisTrackerRounds.equals(comparedTrackerRounds);
        } else {
            return false;
        }
    }
}
