package components.GolfTracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.GolfTracker.GolfTracker1.Course;
import components.GolfTracker.GolfTracker1.Round;
import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code GolfTracker}'s constructor and kernel methods.
 *
 * @author Luke Butcher
 */
public class GolfTrackerTest {

    @Test
    public void testConstructor() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        assertEquals(0, tracker.numRounds());
    }

    @Test
    public void testAddRoundEmpty() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        assertEquals(1, tracker.numRounds());
        assertTrue(tracker.contains(new Round(72, 18, 4, 20, 2026, 1, osu)));
        Sequence rounds = tracker.getAllRounds();
        assertEquals(new Round(72, 18, 4, 20, 2026, 1, osu), rounds.entry(0));
    }

    @Test
    public void testAddRound1() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker.addRound(72, 18, 4, 21, 2026, 1, osu);

        assertEquals(2, tracker.numRounds());
        assertTrue(tracker.contains(new Round(72, 18, 4, 20, 2026, 1, osu)));
        assertTrue(tracker.contains(new Round(72, 18, 4, 21, 2026, 1, osu)));
        Sequence rounds = tracker.getAllRounds();
        // test order
        assertEquals(new Round(72, 18, 4, 20, 2026, 1, osu), rounds.entry(0));
        assertEquals(new Round(72, 18, 4, 21, 2026, 1, osu), rounds.entry(1));
    }

    @Test
    public void testAddRoundMany() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker.addRound(72, 18, 4, 21, 2026, 1, osu);
        tracker.addRound(72, 18, 4, 20, 2026, 2, osu);

        assertEquals(3, tracker.numRounds());
        assertTrue(tracker.contains(new Round(72, 18, 4, 20, 2026, 1, osu)));
        assertTrue(tracker.contains(new Round(72, 18, 4, 21, 2026, 1, osu)));
        assertTrue(tracker.contains(new Round(72, 18, 4, 20, 2026, 2, osu)));
        Sequence rounds = tracker.getAllRounds();
        // test order
        assertEquals(new Round(72, 18, 4, 20, 2026, 1, osu), rounds.entry(0));
        assertEquals(new Round(72, 18, 4, 20, 2026, 2, osu), rounds.entry(1));
        assertEquals(new Round(72, 18, 4, 21, 2026, 1, osu), rounds.entry(2));
    }

    @Test
    public void testDeleteRoundToEmpty() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        tracker.deleteRound(new Round(72, 18, 4, 20, 2026, 1, osu));

        GolfTracker expected = new GolfTrackerOnSequence();
        assertEquals(expected, tracker);
        assertEquals(0, tracker.numRounds());
    }

    @Test
    public void testDeleteRoundTo1() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker.addRound(72, 18, 4, 21, 2026, 1, osu);
        tracker.deleteRound(new Round(72, 18, 4, 20, 2026, 1, osu));

        GolfTracker expected = new GolfTrackerOnSequence();
        assertEquals(expected, tracker);
        assertEquals(1, tracker.numRounds());
        assertTrue(!tracker.contains(new Round(72, 18, 4, 20, 2026, 1, osu)));
    }

    @Test
    public void testDeleteRoundTo2() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker.addRound(72, 18, 4, 21, 2026, 1, osu);
        tracker.addRound(72, 18, 4, 20, 2026, 2, osu);
        tracker.deleteRound(new Round(72, 18, 4, 20, 2026, 2, osu));

        GolfTracker expected = new GolfTrackerOnSequence();
        assertEquals(expected, tracker);
        assertEquals(1, tracker.numRounds());
        assertTrue(!tracker.contains(new Round(72, 18, 4, 20, 2026, 2, osu)));
    }

}
