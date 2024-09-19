package assignment1;
import java.io.*;
import java.util.*;

class ProblemSolver {
    int max_section;
    int time_cost_in_road_switching;
    String[] first_road;
    String[] second_road;
    ProblemSolver(int max_section, int time_cost_in_road_switching,
                  String[] first_road, String[] second_road) {
        this.max_section = max_section;
        this.time_cost_in_road_switching = time_cost_in_road_switching;
        this.first_road = first_road;
        this.second_road = second_road;
    }
    enum Road {
        TuenMunRoad,
        CastlePeakRoad
    }
    public int minimum_time_recursively(int time_costed, int current_section, ArrayList<Road> roads) {
        if (current_section == max_section + 1) {
            return time_costed;
        }
        else {
            int result = 0;
            Road current_road = roads.get(current_section);
            if (current_road == Road.TuenMunRoad) {
                int time_costed_in_TuenMunRoad = Integer.parseInt(first_road[current_section]);
                int time_costed_in_CastlePeakRoad =
                        Integer.parseInt(second_road[current_section]) + time_cost_in_road_switching;
                ArrayList<Road> first_copy = new ArrayList<>(List.copyOf(roads));
                ArrayList<Road> second_copy = new ArrayList<>(List.copyOf(roads));
                first_copy.add(Road.TuenMunRoad);
                second_copy.add(Road.CastlePeakRoad);
                result = Math.min(
                        minimum_time_recursively(
                                time_costed + time_costed_in_TuenMunRoad,
                                current_section + 1,
                                first_copy),
                        minimum_time_recursively(
                                time_costed + time_costed_in_CastlePeakRoad,
                                current_section + 1,
                                second_copy)
                );
            }
            if (current_road == Road.CastlePeakRoad) {
                int time_costed_in_TuenMunRoad =
                        Integer.parseInt(first_road[current_section]) + time_cost_in_road_switching;
                int time_costed_in_CastlePeakRoad = Integer.parseInt(second_road[current_section]);
                ArrayList<Road> first_copy = new ArrayList<>(List.copyOf(roads));
                ArrayList<Road> second_copy = new ArrayList<>(List.copyOf(roads));
                first_copy.add(Road.TuenMunRoad);
                second_copy.add(Road.CastlePeakRoad);
                result = Math.min(
                        minimum_time_recursively(
                                time_costed + time_costed_in_TuenMunRoad,
                                current_section + 1,
                                first_copy),
                        minimum_time_recursively(
                                time_costed + time_costed_in_CastlePeakRoad,
                                current_section + 1,
                                second_copy)
                );
            }
            return result;
        }
    }
}

public class Question2 {
    private static ProblemSolver getProblemSolver() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(reader);
        int num_road_interchanges = Integer.parseInt(buffer.readLine());
        int time_cost_in_road_switching = Integer.parseInt(buffer.readLine());
        String[] time_TuenMunRoads = buffer.readLine().split(" ");
        String[] time_CastlePeakRoads = buffer.readLine().split(" ");

        return new ProblemSolver(num_road_interchanges ,
                time_cost_in_road_switching, time_TuenMunRoads, time_CastlePeakRoads);
    }
    public static void main(String[] args) throws IOException {
        ProblemSolver problem = getProblemSolver();
        System.out.println("The minimum time needed is "
                + Math.min(
                        problem.minimum_time_recursively(0,
                                0, new ArrayList<>(List.of(ProblemSolver.Road.TuenMunRoad))),
                        problem.minimum_time_recursively(0,
                                0, new ArrayList<>(List.of(ProblemSolver.Road.CastlePeakRoad)))
                ) + "."
        );
    }
}
