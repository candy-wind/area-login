package com.example.demo;


import java.io.File;
import java.nio.file.Paths;

/**
 * ACO - Ant Colony Optimization Meta-heuristic
 *
 * Reference book: Ant Colony Optimization.
 * Authors: Marco Dorigo and Thomas Stützle
 * Links:
 *  -> https://mitpress.mit.edu/books/ant-colony-optimization
 *  -> http://www.aco-metaheuristic.org/
 *
 * This algorithm present the implementation of ACO for TSP problems.
 */
public class Program {
    double bestSoFar=Double.MAX_VALUE;
    int[] bestTourSoFar;
    public static void main(String[] args) throws Exception {
        long start=System.currentTimeMillis();
        String tspPath = (new File(".")).getCanonicalPath();
        tspPath = Paths.get(tspPath, "tsp").toAbsolutePath().toString();
        String tspFiles[]={"tsp.csv"};
        Program app = new Program();
        // Test more simulations
        for(String tspFile : tspFiles) {
            System.out.println("\nProblem: " + tspFile);
            app.startApplication(tspPath, tspFile);
        }
        long end=System.currentTimeMillis();
        System.out.println((end-start)/1000f+"second");
    }

    // Main part of the algorithm
    public void startApplication(String path, String file) {

        Environment environment = new Environment(TspReader.getDistances(path, file));
        // Startup part
        environment.generateNearestNeighborList();
        environment.generateAntPopulation();
        environment.generateEnvironment();

        // Repeat the ants behavior by n times
        int n = 0;
        while(n < Parameters.iterationsMax) {
            environment.constructSolutions();
            environment.updatePheromone();
            showBestAnt(environment);
            System.out.println(bestSoFar);
            n++;
        }
        System.out.println();
        try { Thread.sleep(3000); } catch (Exception ex) {}
        for(int i:bestTourSoFar){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Finished");
    }

    public void showBestAnt(Environment environment) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double total=0;
        Ant bestAnt = null;
        for(Ant ant : environment.getAnts()) {
            if(ant.getTourCost() < min) {
                min = ant.getTourCost();
                bestAnt = ant;
            }
            if(ant.getTourCost() > max) {
                max = ant.getTourCost();
            }
            total += ant.getTourCost();
        }
        if(min < bestSoFar) {
            bestSoFar = min;
            bestTourSoFar = bestAnt.getTour().clone();
        }
    }
}
