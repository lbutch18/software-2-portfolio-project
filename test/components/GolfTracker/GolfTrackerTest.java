package components.GolfTracker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.GolfTracker.GolfTrackerOnSequence.Course;
import components.GolfTracker.GolfTrackerOnSequence.Round;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * JUnit test fixture for {@code GolfTracker}'s enhanced/secondary methods.
 *
 * @author Luke Butcher
 */
public class GolfTrackerTest {

    @Test
    public void testBestRound1() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        Round actual = actualTracker.bestRound();
        Round expected = new Round(72, 18, 4, 20, 2026, 1, osu);

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual);
    }

    @Test
    public void testBestRound2() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(68, 18, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(68, 18, 4, 20, 2026, 2, osu);

        Round actual = actualTracker.bestRound();
        Round expected = new Round(68, 18, 4, 20, 2026, 2, osu);

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual);
    }

    @Test
    public void testBestRound3() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(68, 18, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(68, 18, 4, 20, 2026, 2, osu);

        Round actual = actualTracker.bestRound();
        Round expected = new Round(68, 18, 4, 20, 2026, 2, osu);

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual);
    }

    @Test
    public void testAverageScore3All18() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(68, 18, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(68, 18, 4, 20, 2026, 2, osu);

        double actual = actualTracker.averageScore(18);
        double expected = 72.66666666667;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testAverageScore3MixedTwo18() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(37, 9, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(37, 9, 4, 20, 2026, 2, osu);

        double actual = actualTracker.averageScore(18);
        double expected = 75;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testAverageScore3MixedOne9() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(37, 9, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(37, 9, 4, 20, 2026, 2, osu);

        double actual = actualTracker.averageScore(9);
        double expected = 37;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testAverageScore1() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        double actual = actualTracker.averageScore(18);
        double expected = 72;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testRoundsAtCourseEmpty() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);

        Sequence<Round> actual = actualTracker.roundsAtCourse(osu);
        Sequence<Round> expected = new Sequence1L<>();

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual);
    }

    @Test
    public void testRoundsAtCourse1AllAtCourse() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        Sequence<Round> actual = actualTracker.roundsAtCourse(osu);
        Sequence<Round> expected = new Sequence1L<>();
        expected.add(0, new Round(72, 18, 4, 20, 2026, 1, osu));

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual);
    }

    @Test
    public void testRoundsAtCourse3AllAtCourse() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(37, 9, 4, 20, 2026, 2, osu);
        expectedTracker.addRound(37, 9, 4, 20, 2026, 2, osu);

        Sequence<Round> actual = actualTracker.roundsAtCourse(osu);
        Sequence<Round> expected = new Sequence1L<>();
        expected.add(0, new Round(72, 18, 4, 20, 2026, 1, osu));
        expected.add(1, new Round(37, 9, 4, 20, 2026, 2, osu));
        expected.add(2, new Round(78, 18, 4, 21, 2026, 1, osu));

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual);
    }

    @Test
    public void testRoundsAtCourse3DifferentCourses() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        Course raymond = new Course("Raymond Memorial Front", 36.2, 118);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(37, 9, 4, 20, 2026, 2, raymond);
        expectedTracker.addRound(37, 9, 4, 20, 2026, 2, raymond);

        Sequence<Round> actualOSU = actualTracker.roundsAtCourse(osu);
        Sequence<Round> expectedOSU = new Sequence1L<>();
        expectedOSU.add(0, new Round(72, 18, 4, 20, 2026, 1, osu));
        expectedOSU.add(1, new Round(78, 18, 4, 21, 2026, 1, osu));

        Sequence<Round> actualRaymond = actualTracker.roundsAtCourse(raymond);
        Sequence<Round> expectedRaymond = new Sequence1L<>();
        expectedRaymond.add(0, new Round(37, 9, 4, 20, 2026, 2, raymond));

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expectedOSU, actualOSU);
        assertEquals(expectedRaymond, actualRaymond);
    }

    @Test
    public void testReplaceRound1() {
        GolfTracker actual = new GolfTrackerOnSequence();
        GolfTracker expected = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actual.addRound(72, 18, 4, 20, 2026, 1, osu);
        expected.addRound(73, 18, 4, 20, 2026, 1, osu);

        actual.replaceRound(new Round(72, 18, 4, 20, 2026, 1, osu),
                new Round(73, 18, 4, 20, 2026, 1, osu));

        assertEquals(expected, actual);
    }

    @Test
    public void testReplaceRound3() {
        GolfTracker actual = new GolfTrackerOnSequence();
        GolfTracker expected = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actual.addRound(78, 18, 4, 21, 2026, 1, osu);
        expected.addRound(78, 18, 4, 21, 2026, 1, osu);
        actual.addRound(72, 18, 4, 20, 2026, 1, osu);
        expected.addRound(73, 18, 4, 20, 2026, 1, osu);
        actual.addRound(37, 9, 4, 20, 2026, 2, osu);
        expected.addRound(37, 9, 4, 20, 2026, 2, osu);

        actual.replaceRound(new Round(72, 18, 4, 20, 2026, 1, osu),
                new Round(73, 18, 4, 20, 2026, 1, osu));

        assertEquals(expected, actual);
    }

}
