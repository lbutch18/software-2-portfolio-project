package components.GolfTracker;

import components.GolfTracker.GolfTracker1.Course;
import components.GolfTracker.GolfTracker1.Round;
import components.sequence.Sequence;

/**
 * {@code GolfTrackerKernel} enhanced with secondary methods. Any more ideas for
 * some interesting enhanced methods?
 */
public interface GolfTracker extends GolfTrackerKernel {
    /**
     * Simple USGA-based handicap calculation for the rounds in this.
     *
     * @return the handicap given the rounds in this
     * @requires number of rounds in this >= 3
     * @ensures calculateHandicap = handicap rounded to tenths place
     */
    double calculateHandicap();

    /**
     * Return the best round by diff in the tracker.
     *
     * @return the round with the lowest differential
     * @requires number of rounds in this > 0
     * @ensures bestRound = round with least differential in this
     * @aliases bestRound
     */
    Round bestRound();

    /**
     * Return the average differential in the tracker.
     *
     * @return the average differential of all rounds in this
     * @requires number of rounds in this > 0
     * @ensures averageDiff = mean differential of all Rounds in this
     */
    double averageDiff();

    /**
     * Return the average score for rounds with given holes played in the
     * tracker.
     *
     * @param holesPlayed
     *            the number of holes played for which the average score is to
     *            be calculated
     * @return the average score of all rounds in this
     * @requires number of rounds in this > 0
     * @ensures averageScore = mean score of all Rounds in this
     */
    double averageScore(int holesPlayed);

    /**
     * Returns the Sequence of all rounds played at the given course.
     *
     * @param course
     *            the course to get the rounds at
     * @return Sequence of Rounds played the given Course
     * @requires number of rounds played at course > 0
     * @ensures roundsAtCourse = Sequence of all Rounds played at course
     */
    Sequence<Round> roundsAtCourse(Course course);

    /**
     * Intended to allow for editing of rounds. Replaces a round in the tracker
     * with the new round.
     *
     * @param oldRound
     *            the old round to be removed
     * @param newRound
     *            the new round to be added
     * @requires oldRound is a Round in this
     * @ensures oldRound is not in this and copy of newRound is in this
     * @updates this
     */
    void replaceRound(Round oldRound, Round newRound);
}
