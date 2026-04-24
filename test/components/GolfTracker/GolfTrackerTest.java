package components.GolfTracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void testCalculateHandicap0() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();

        double actual = actualTracker.calculateHandicap();
        double expected = 0;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testCalculateHandicap1() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);

        double actual = actualTracker.calculateHandicap();
        double expected = 0;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testCalculateHandicapMany() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(75, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(75, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 22, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 22, 2026, 1, osu);

        double actual = actualTracker.calculateHandicap();
        double expected = -2.0;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testAverageDiff1() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(75, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(75, 18, 4, 20, 2026, 1, osu);

        double actual = actualTracker.averageDiff();
        double expected = 2.8;

        assertEquals(expectedTracker, actualTracker);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testAverageDiffMany() {
        GolfTracker actualTracker = new GolfTrackerOnSequence();
        GolfTracker expectedTracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        actualTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        expectedTracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        actualTracker.addRound(75, 18, 4, 21, 2026, 1, osu);
        expectedTracker.addRound(75, 18, 4, 21, 2026, 1, osu);
        actualTracker.addRound(78, 18, 4, 22, 2026, 1, osu);
        expectedTracker.addRound(78, 18, 4, 22, 2026, 1, osu);

        double actual = actualTracker.averageDiff();
        double expected = 2.83333333333;

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

    @Test
    public void testToStringEmpty() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        assertEquals("<>", tracker.toString());
    }

    @Test
    public void testToStringOne() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        assertEquals(
                "<Round data:\n" + "Strokes: 72\n" + "Holes Played: 18\n"
                        + "Date: 4/20/2026\n" + "ID: 1\n"
                        + "Course: Course[name=OSU, rating=72.0, slope=120.0]>",
                tracker.toString());
    }

    @Test
    public void testToStringTwo() {
        GolfTracker tracker = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker.addRound(75, 18, 4, 21, 2026, 1, osu);
        assertEquals(
                "<Round data:\nStrokes: 72\nHoles Played: 18\nDate: 4/20/2026\nID: 1\nCourse: Course[name=OSU, rating=72.0, slope=120.0],Round data:\nStrokes: 75\nHoles Played: 18\nDate: 4/21/2026\nID: 1\nCourse: Course[name=OSU, rating=72.0, slope=120.0]>",
                tracker.toString());
    }

    @Test
    public void testEqualsEmpty() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();

        assertTrue(tracker1.equals(tracker2));
        assertTrue(tracker2.equals(tracker1));
    }

    @Test
    public void testEquals1True() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 20, 2026, 1, osu);

        assertTrue(tracker1.equals(tracker2));
        assertTrue(tracker2.equals(tracker1));
    }

    @Test
    public void testEquals2True() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker1.addRound(75, 18, 4, 20, 2026, 2, osu);
        tracker2.addRound(75, 18, 4, 20, 2026, 2, osu);

        assertTrue(tracker1.equals(tracker2));
        assertTrue(tracker2.equals(tracker1));
    }

    @Test
    public void testEquals2False() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker1.addRound(75, 18, 4, 20, 2026, 2, osu);
        tracker2.addRound(74, 18, 4, 20, 2026, 2, osu);

        assertFalse(tracker1.equals(tracker2));
        assertFalse(tracker2.equals(tracker1));
    }

    @Test
    public void testEqualsMixedFalse() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker1.addRound(75, 18, 4, 20, 2026, 2, osu);

        assertFalse(tracker1.equals(tracker2));
        assertFalse(tracker2.equals(tracker1));
    }

    @Test
    public void testEquals1False() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 21, 2026, 1, osu);

        assertFalse(tracker1.equals(tracker2));
        assertFalse(tracker2.equals(tracker1));
    }

    @Test
    public void testHashcodeEmptySame() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();

        assertEquals(tracker1.hashCode(), tracker2.hashCode());
    }

    @Test
    public void testHashcode1Same() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 20, 2026, 1, osu);

        assertEquals(tracker1.hashCode(), tracker2.hashCode());
    }

    @Test
    public void testHashcode2Same() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker1.addRound(75, 18, 4, 20, 2026, 2, osu);
        tracker2.addRound(75, 18, 4, 20, 2026, 2, osu);

        assertEquals(tracker1.hashCode(), tracker2.hashCode());
    }

    @Test
    public void testHashcode3Same() {
        GolfTracker tracker1 = new GolfTrackerOnSequence();
        GolfTracker tracker2 = new GolfTrackerOnSequence();
        Course osu = new Course("OSU", 72, 120);
        Course raymond = new Course("Raymond Memorial Front", 36.2, 118);

        tracker1.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker1.addRound(37, 9, 4, 20, 2026, 2, raymond);
        tracker1.addRound(78, 18, 4, 21, 2026, 1, osu);
        tracker2.addRound(78, 18, 4, 21, 2026, 1, osu);
        tracker2.addRound(72, 18, 4, 20, 2026, 1, osu);
        tracker2.addRound(37, 9, 4, 20, 2026, 2, raymond);

        assertTrue(tracker1.equals(tracker2));
        assertEquals(tracker1.hashCode(), tracker2.hashCode());
    }

}
