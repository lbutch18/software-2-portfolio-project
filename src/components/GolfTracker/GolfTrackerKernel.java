package components.GolfTracker;

import components.GolfTracker.GolfTrackerOnSequence.Course;
import components.GolfTracker.GolfTrackerOnSequence.Round;
import components.sequence.Sequence;
import components.standard.Standard;

/**
 * Component for tracking of golf rounds. Each round is assigned to a course,
 * with the tracker represented as a Sequence of Rounds. Allows for simple
 * handicap calculation, stat tracking, etc.
 */
public interface GolfTrackerKernel extends Standard<GolfTracker> {
    /**
     * Adds a round to the tracker.
     *
     * @param strokes
     *            The score for the round
     * @param holesPlayed
     *            The number of holes played
     * @param month
     *            The month the round was played
     * @param day
     *            The day the round was played
     * @param year
     *            The year the round was played
     * @param id
     *            The chronological order of the round for the day it was played
     *            (when two rounds played in thes same day, 1 is the first
     *            round, 2 is the second, etc.)
     * @param course
     *            The Course at which the round was played
     * @updates this
     * @ensures new round is added in chronological order in this
     */
    void addRound(int strokes, int holesPlayed, int month, int day, int year,
            int id, Course course);

    /**
     * Remove a round from the tracker.
     *
     * @param round
     *            the round to be removed
     * @requires round is in this.roundEntries
     * @updates this
     * @ensures round is no longer in this
     */
    void deleteRound(Round round);

    /**
     * Get the Sequence of all rounds in the tracker.
     *
     * @return all Rounds in the tracker
     * @ensures getAllRounds = all rounds in the tracker
     */
    Sequence<Round> getAllRounds();

    /**
     * Get the number of all rounds in the tracker.
     *
     * @return number of Rounds in the tracker
     * @ensures numRounds = size of the tracker
     */
    int numRounds();

    /**
     * Returns true if round is in this, false otherwise.
     *
     * @param round
     *            the round to check
     * @return whether
     */
    boolean contains(Round round);
}
