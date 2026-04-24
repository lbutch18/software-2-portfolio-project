package components.GolfTracker;

import components.GolfTracker.GolfTrackerOnSequence.Course;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * GolfTracker Use case example: basic CLI tool to calculate the user's handicap
 * after they enter their rounds.
 */
public class HandicapTracker {

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        GolfTracker tracker = new GolfTrackerOnSequence();

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

            Course nextCourse = new Course(courseName, rating, slope);
            tracker.addRound(score, holesPlayed, month, day, year, 0,
                    nextCourse); // Assume no rounds on same day for the POC
            out.println(
                    "Add another round? Press ENTER to continue or type anything else to quit.");
        }

        out.println("Rounds added: " + tracker.getAllRounds());
        if (tracker.numRounds() >= 3) {
            out.println("Your handicap: " + tracker.calculateHandicap());
        } else {
            out.println("Not enough rounds for a handicap.");
        }

        out.close();
        in.close();
    }
}
