import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    private static final String SAVE_FILE = "save.dat";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        loadTasks(tasks);

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n1) Display list");
            System.out.println("2) Add task");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();
            if (choice.equals("1")) {
                displayTasks(tasks);
            } else if (choice.equals("2")) {
                addTask(tasks, input);
                sortTasksByRank(tasks);
            } else if (choice.equals("3")) {
                saveTasks(tasks);
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        input.close();
    }

    private static void loadTasks(ArrayList<Task> tasks) {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return;
        }

        try (Scanner fileInput = new Scanner(file)) {
            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(":");
                if (parts.length != 2) {
                    continue;
                }
                int rank = Integer.parseInt(parts[0]);
                String name = parts[1];
                tasks.add(new Task(rank, name));
            }
        } catch (IOException e) {
            System.out.println("Could not read save file: " + e.getMessage());
        }
    }

    private static void displayTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks saved.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void addTask(ArrayList<Task> tasks, Scanner input) {
        System.out.print("Enter task name: ");
        String name = input.nextLine();
        System.out.print("Enter task rank: ");
        String rankText = input.nextLine();

        try {
            int rank = Integer.parseInt(rankText);
            tasks.add(new Task(rank, name));
        } catch (NumberFormatException e) {
            System.out.println("Rank must be a number.");
        }
    }

    private static void sortTasksByRank(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(j).getRank() < tasks.get(minIndex).getRank()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Task temp = tasks.get(i);
                tasks.set(i, tasks.get(minIndex));
                tasks.set(minIndex, temp);
            }
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(SAVE_FILE)) {
            for (Task task : tasks) {
                writer.println(task.getRank() + ":" + task.getName());
            }
        } catch (IOException e) {
            System.out.println("Could not write save file: " + e.getMessage());
        }
    }
}
