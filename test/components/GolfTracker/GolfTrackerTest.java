package components.GolfTracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.GolfTracker.GolfTrackerOnSequence.Course;
import components.GolfTracker.GolfTrackerOnSequence.Round;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

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
        expected.addRound(72, 18, 4, 21, 2026, 1, osu);

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
        expected.addRound(72, 18, 4, 20, 2026, 1, osu);
        expected.addRound(72, 18, 4, 21, 2026, 1, osu);
        assertEquals(expected, tracker);
        assertEquals(2, tracker.numRounds());
        assertTrue(!tracker.contains(new Round(72, 18, 4, 20, 2026, 2, osu)));
    }

    @Test
    public void testGetAllRoundsEmpty() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Sequence<Round> expected = new Sequence1L<>();

        Sequence<Round> actual = tracker.getAllRounds();

        assertEquals(expected, actual);
        assertEquals(new GolfTrackerOnSequence(), tracker);
    }

    @Test
    public void testGetAllRounds1() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        Sequence<Round> expected = new Sequence1L<>();
        expected.add(0, new Round(72, 18, 4, 20, 2026, 1, osu));

        Sequence<Round> actual = actualTracker.getAllRounds();

        assertEquals(expected, actual);
        assertEquals(expectedTracker, actualTracker);
    }

    @Test
    public void testGetAllRounds2() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(72, 18, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 2, osu);
        Sequence<Round> expected = new Sequence1L<>();
        expected.add(0, new Round(72, 18, 4, 20, 2026, 1, osu));
        expected.add(1, new Round(72, 18, 4, 20, 2026, 2, osu));

        Sequence<Round> actual = actualTracker.getAllRounds();

        assertEquals(expected, actual);
        assertEquals(expectedTracker, actualTracker);
    }

    @Test
    public void testNumRounds2() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(72, 18, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 2, osu);

        int actual = actualTracker.numRounds();

        assertEquals(2, actual);
        assertEquals(expectedTracker, actualTracker);
    }

    @Test
    public void testNumRounds1() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        int actual = actualTracker.numRounds();

        assertEquals(1, actual);
        assertEquals(expectedTracker, actualTracker);
    }

    @Test
    public void testNumRoundsEmpty() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();

        int actual = actualTracker.numRounds();

        assertEquals(0, actual);
        assertEquals(new GolfTrackerOnSequence(), actualTracker);
    }

    @Test
    public void testContainsEmpty() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);

        boolean actual = actualTracker
                .contains(new Round(72, 18, 4, 20, 2026, 1, osu));

        assertEquals(false, actual);
        assertEquals(new GolfTrackerOnSequence(), actualTracker);
    }

    @Test
    public void testContains2True() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(72, 18, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 2, osu);

        boolean actual = actualTracker
                .contains(new Round(72, 18, 4, 20, 2026, 1, osu));

        assertEquals(true, actual);
        assertEquals(expectedTracker, actualTracker);
    }

    @Test
    public void testContains1True() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        boolean actual = actualTracker
                .contains(new Round(72, 18, 4, 20, 2026, 1, osu));

        assertEquals(true, actual);
        assertEquals(expectedTracker, actualTracker);
    }

    @Test
    public void testContains1False() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        boolean actual = actualTracker
                .contains(new Round(72, 18, 4, 21, 2026, 1, osu));

        assertEquals(false, actual);
        assertEquals(expectedTracker, actualTracker);
    }

    @Test
    public void testContains2False() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(72, 18, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 2, osu);

        boolean actual = actualTracker
                .contains(new Round(72, 18, 4, 21, 2026, 1, osu));

        assertEquals(false, actual);
        assertEquals(expectedTracker, actualTracker);
    }

}
