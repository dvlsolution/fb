package org;

import java.util.*;

public class Main {
    /*
        You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the second
        project is dependent on the first project). All of a project's dependencies must be build before the project is.
        Find a build order that will allow the projects to be build. If there is no valid build order, return an error.

        Example
        Input:
            projects: a,b,c,d,e,f,d,g
            dependencies: (c,f),(b,f),(a,f),(a,b),(a,c),(e,a),(e,b),(g,d)
        Output: d,g,f,e,a,b,d,c
     */
    // so called topological sort, O(p+d)
    enum State {COMPLETED, PARTIAL, BLANK}

    class Project {
        State state = State.BLANK;
        List<Project> children = new ArrayList<>();
        Map<String, Project> map = new HashMap<>();
        String name;

        Project(String name) {
            this.name = name;
        }

        void addNeighbor(Project node) {
            if (!map.containsKey(node.name)) {
                children.add(node);
                map.put(node.name, node);
            }
        }
    }

    class Graph {
        List<Project> nodes = new ArrayList<>();
        Map<String, Project> map = new HashMap<>();

        Project getOrCreateNode(String name) {
            if (!map.containsKey(name)) {
                Project node = new Project(name);
                nodes.add(node);
                map.put(name, node);
            }
            return map.get(name);
        }

        void addEdge(String startName, String endName) {
            Project start = getOrCreateNode(startName);
            Project end = getOrCreateNode(endName);
            start.addNeighbor(end);
        }
    }

    Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        for (String project : projects) {
            graph.getOrCreateNode(project);
        }

        for (String[] dependency : dependencies) {
            String first = dependency[0];
            String second = dependency[1];
            graph.addEdge(first, second);
        }

        return graph;
    }

    Stack<Project> orderProjects(List<Project> projects) {
        Stack<Project> stack = new Stack<>();
        for (Project project : projects) {
            if (project.state == State.BLANK) {
                if (!doDFS(project, stack)) {
                    return null;
                }
            }
        }
        return stack;
    }

    boolean doDFS(Project project, Stack<Project> stack) {
        if (project.state == State.PARTIAL) {
            return false; // cycle
        }

        if (project.state == State.BLANK) {
            project.state = State.PARTIAL; // mark project to as in operation
            List<Project> children = project.children;
            for (Project child : children) {
                if (!doDFS(child, stack)) {
                    return false;
                }
            }
            project.state = State.COMPLETED; // mark project as completed
            stack.push(project);
        }

        return true;
    }

    public static void main(String[] args) {
        Main main = new Main();
        Graph graph = main.buildGraph(
                new String[] { "a", "b", "c", "d", "e" ,"f", "d", "g" },
                new String[][] { {"c", "f"}, {"b", "f"}, {"a", "f"}, {"a", "b"}, {"a", "c"}, {"e", "a"}, {"e", "b"}, {"g", "d"} }
        );

        Stack<Project> result = main.orderProjects(graph.nodes);
        for (Project project : result) {
            System.out.println(project.name);
        }
        System.out.println("DONE");
    }
}
