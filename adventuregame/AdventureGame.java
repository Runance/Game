package adventuregame;

import java.util.Scanner;

public class AdventureGame implements Playable, Attackable, Runnable {
    private String playerName;
    private int playerHealth = 100;
    private int[] enemyHealths = {30, 40, 50}; 
    private int currentEnemyIndex = 0;

    @Override
    public void startGame(String playerName) {
        this.playerName = playerName;
        System.out.println("Welcome, " + playerName + "! Press 1 or 2 to select your game mode.");
        System.out.println("1 - Story");
        System.out.println("2 - Survival");
    }
    
    @Override
    public int attack() {
        int damage = (int) (Math.random() * 20) + 1;
        enemyHealths[currentEnemyIndex] -= damage;
        return damage;
    }

    @Override
    public void runAway() {
        System.out.println(playerName + " ran away from the enemy!");
    }

    public void playSurvivalMode() {
        System.out.println("Survival mode - defeat enemies one by one!");
        for (currentEnemyIndex = 0; currentEnemyIndex < enemyHealths.length; currentEnemyIndex++) {
            System.out.println("\nYou encounter Enemy " + (currentEnemyIndex + 1) + "!");

            while (enemyHealths[currentEnemyIndex] > 0 && playerHealth > 0) {
                System.out.println("\nPress P to attack, R to run away.");
                char action = new Scanner(System.in).next().charAt(0);

                switch (action) {
                    case 'P':
                    case 'p':
                        int damage = attack();
                        System.out.println("You attacked the enemy and dealt " + damage + " damage.");
                        break;
                    case 'R':
                    case 'r':
                        runAway();
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        continue;
                }

             
                System.out.println("Player: " + playerName + " | Health: " + playerHealth);
                System.out.println("Enemy " + (currentEnemyIndex + 1) + " | Health: " + enemyHealths[currentEnemyIndex]);
            }

            if (playerHealth <= 0) {
                System.out.println("Game over. You were defeated by Enemy " + (currentEnemyIndex + 1) + ".");
                return;
            } else {
                System.out.println("You defeated Enemy " + (currentEnemyIndex + 1) + "!");
            }
        }

        System.out.println("Congratulations! You defeated all enemies in survival mode.");
    }


    public void playStoryMode() {
        System.out.println("Once upon a time...");
        String story = "In a mysterious land far, far away... "
                + "\nThere is a hero in the land as same as king arthur"
                + "\nand he had 3 enemies need to defeat.";
        for (int i = 0; i < story.length(); i++) {
            System.out.print(story.charAt(i));
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nPress any key to continue...");

        new Scanner(System.in).nextLine();

   
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdventureGame game = new AdventureGame();

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        game.startGame(playerName);

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                game.playStoryMode();
                break;
            case 2:
                game.playSurvivalMode();
                break;
            default:
                System.out.println("Invalid choice. Exiting game.");
                return;
        }
    }
}
