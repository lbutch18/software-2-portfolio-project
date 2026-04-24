package components.GolfTracker;

import components.GolfTracker.GolfTrackerOnSequence.Course;
import components.GolfTracker.GolfTrackerOnSequence.Round;
import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;

/**
 * GolfTracker use case example: a class that provides more interesting
 * analytics on top of what is already offered by GolfTracker.
 */
public class CourseAnalytics {

    private GolfTracker tracker;

    public CourseAnalytics() {
        this.tracker = new GolfTrackerOnSequence();
    }

    public Set<Course> getAllCourses() {
        Sequence<Round> rounds = this.tracker.getAllRounds();
        Set<Course> courses = new Set1L<>();
        for (Round round : rounds) {
            if (!courses.contains(round.getCourse())) {
                courses.add(round.getCourse());
            }
        }

        return courses;
    }

    // return course with lowest avg diff
    public Course bestCourse() {
        Sequence<Round> rounds = this.tracker.getAllRounds();
        Map<Course, Sequence<Double>> coursesToRoundDiffs = new Map1L<>();

        for (Round round : rounds) {
            if (!coursesToRoundDiffs.hasKey(round.getCourse())) {
                coursesToRoundDiffs.add(round.getCourse(), new Sequence1L<>());
            } else {
                Sequence<Double> newSeq = new Sequence1L<>();
                newSeq.append(coursesToRoundDiffs.value(round.getCourse()));
                newSeq.add(newSeq.length(), round.getDiff());
                coursesToRoundDiffs.replaceValue(round.getCourse(), newSeq);
            }
        }

        Map.Pair<Course, Sequence<Double>> firstPair = coursesToRoundDiffs
                .removeAny();
        Course minCourse = firstPair.key();
        double minAvg = Double.MAX_VALUE;
        for (double diff : firstPair.value()) {
            minAvg++;
        }
        minAvg /= firstPair.value().length();
        for (Map.Pair<Course, Sequence<Double>> pair : coursesToRoundDiffs) {
            int avg = 0;
            for (double diff : pair.value()) {
                avg++;
            }
            avg /= pair.value().length();

            if (avg < minAvg) {
                minAvg = avg;
                minCourse = pair.key();
            }
        }

        return minCourse;

    }

    /* Could add more methods */

}
