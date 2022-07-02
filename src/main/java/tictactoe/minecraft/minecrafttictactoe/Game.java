package tictactoe.minecraft.minecrafttictactoe;

import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;


import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private Player player1;
    private Player player2;
    private ArrayList<Integer> player1Positions = new ArrayList<>();
    private ArrayList<Integer> player2Positions = new ArrayList<>();
    private boolean isFinished = false;
    private boolean isPlayer1Turn = false;
    private boolean isPlayer2Turn = false;
    private boolean isAccepted = false;
    private boolean isLoaded = false;
    private int loadedId = 0;

    private Location[][] gameBoard;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        World world = player1.getLocation().getWorld();
        int x = player1.getLocation().getBlockX();
        int y = player1.getLocation().getBlockY();
        int z = player1.getLocation().getBlockZ();
        this.gameBoard = new Location[][]{
                {new Location(world, x - 2, y + 2, z - 5), new Location(world, x - 1, y + 2, z - 5),
                        new Location(world, x, y + 2, z - 5)},
                {new Location(world, x - 2, y + 1, z - 5), new Location(world, x - 1, y + 1, z - 5),
                        new Location(world, x, y + 1, z - 5)},
                {new Location(world, x - 2, y, z - 5), new Location(world, x - 1, y, z - 5),
                        new Location(world, x, y, z - 5)}
        };
    }

    public ArrayList<Integer> getPlayer1Positions() {
        return player1Positions;
    }

    public ArrayList<Integer> getPlayer2Positions() {
        return player2Positions;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setPlayer1Positions(ArrayList<Integer> player1Positions) {
        this.player1Positions = player1Positions;
    }

    public void setPlayer2Positions(ArrayList<Integer> player2Positions) {
        this.player2Positions = player2Positions;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public boolean isPlayer2Turn() {
        return isPlayer2Turn;
    }

    public void setPlayer1Turn(boolean player1Turn) {
        isPlayer1Turn = player1Turn;
    }

    public void setPlayer2Turn(boolean player2turn) {
        isPlayer2Turn = player2turn;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setGameBoard(Player player1) {
        World world = player1.getLocation().getWorld();
        int x = player1.getLocation().getBlockX();
        int y = player1.getLocation().getBlockY();
        int z = player1.getLocation().getBlockZ();
        this.gameBoard = new Location[][]{
                {new Location(world, x - 2, y + 2, z - 5), new Location(world, x - 1, y + 2, z - 5),
                        new Location(world, x, y + 2, z - 5)},
                {new Location(world, x - 2, y + 1, z - 5), new Location(world, x - 1, y + 1, z - 5),
                        new Location(world, x, y + 1, z - 5)},
                {new Location(world, x - 2, y, z - 5), new Location(world, x - 1, y, z - 5),
                        new Location(world, x, y, z - 5)}
        };
    }

    public void showTable() {
        for (Location[] row : gameBoard) {
            for (Location c : row) {
                c.getBlock().setType(Material.WHITE_WOOL);
            }
        }
    }
    public Location[][] getGameBoard() {
        return gameBoard;
    }

    public void placePiece(int pos, Player player) {
        Material playerBlock;
        if (player == player1) {
            playerBlock = Material.BLUE_WOOL;
            player1Positions.add(pos);
        } else {
            playerBlock = Material.RED_WOOL;
            player2Positions.add(pos);
        }

        switch (pos) {
            case 1 -> gameBoard[0][0].getBlock().setType(playerBlock);
            case 2 -> gameBoard[0][1].getBlock().setType(playerBlock);
            case 3 -> gameBoard[0][2].getBlock().setType(playerBlock);
            case 4 -> gameBoard[1][0].getBlock().setType(playerBlock);
            case 5 -> gameBoard[1][1].getBlock().setType(playerBlock);
            case 6 -> gameBoard[1][2].getBlock().setType(playerBlock);
            case 7 -> gameBoard[2][0].getBlock().setType(playerBlock);
            case 8 -> gameBoard[2][1].getBlock().setType(playerBlock);
            case 9 -> gameBoard[2][2].getBlock().setType(playerBlock);
        }
    }

    public void loadPlacePiece(ArrayList<Integer> player1Positions, ArrayList<Integer> player2Positions) {
        for (Integer player1Position : player1Positions) {
            Material playerBlock = Material.BLUE_WOOL;
            switch (player1Position) {
                case 1 -> gameBoard[0][0].getBlock().setType(playerBlock);
                case 2 -> gameBoard[0][1].getBlock().setType(playerBlock);
                case 3 -> gameBoard[0][2].getBlock().setType(playerBlock);
                case 4 -> gameBoard[1][0].getBlock().setType(playerBlock);
                case 5 -> gameBoard[1][1].getBlock().setType(playerBlock);
                case 6 -> gameBoard[1][2].getBlock().setType(playerBlock);
                case 7 -> gameBoard[2][0].getBlock().setType(playerBlock);
                case 8 -> gameBoard[2][1].getBlock().setType(playerBlock);
                case 9 -> gameBoard[2][2].getBlock().setType(playerBlock);
            }
        }
        for (Integer player2Position : player2Positions) {
            Material playerBlock = Material.RED_WOOL;
            switch (player2Position) {
                case 1 -> gameBoard[0][0].getBlock().setType(playerBlock);
                case 2 -> gameBoard[0][1].getBlock().setType(playerBlock);
                case 3 -> gameBoard[0][2].getBlock().setType(playerBlock);
                case 4 -> gameBoard[1][0].getBlock().setType(playerBlock);
                case 5 -> gameBoard[1][1].getBlock().setType(playerBlock);
                case 6 -> gameBoard[1][2].getBlock().setType(playerBlock);
                case 7 -> gameBoard[2][0].getBlock().setType(playerBlock);
                case 8 -> gameBoard[2][1].getBlock().setType(playerBlock);
                case 9 -> gameBoard[2][2].getBlock().setType(playerBlock);
            }
        }
        List<Integer> combined = Stream.of(player1Positions, player2Positions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        ArrayList<Integer> emptyPositions = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (!combined.contains(i))
                emptyPositions.add(i);
        }
        for (Integer emptyPos : emptyPositions) {
            Material playerBlock = Material.WHITE_WOOL;
            switch (emptyPos) {
                case 1 -> gameBoard[0][0].getBlock().setType(playerBlock);
                case 2 -> gameBoard[0][1].getBlock().setType(playerBlock);
                case 3 -> gameBoard[0][2].getBlock().setType(playerBlock);
                case 4 -> gameBoard[1][0].getBlock().setType(playerBlock);
                case 5 -> gameBoard[1][1].getBlock().setType(playerBlock);
                case 6 -> gameBoard[1][2].getBlock().setType(playerBlock);
                case 7 -> gameBoard[2][0].getBlock().setType(playerBlock);
                case 8 -> gameBoard[2][1].getBlock().setType(playerBlock);
                case 9 -> gameBoard[2][2].getBlock().setType(playerBlock);
            }
        }
    }

    public String winnerCheck() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> bottomCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List<Integer>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(bottomCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List<Integer> l : winning) {
            if (player1Positions.containsAll(l)) {
                isFinished = true;
                summonFireworks(player1);
                return "Player \"" + player1.getDisplayName() + "\" won!";
            } else if (player2Positions.containsAll(l)) {
                isFinished = true;
                summonFireworks(player2);
                return "Player \"" + player2.getDisplayName() + "\" won!";
            } else if (player1Positions.size() + player2Positions.size() == 9) {
                isFinished = true;
                return "TIE!";
            }
        }

        return "";
    }

    private Color getColor(int i) {
        Color c = null;
        if (i == 1) {
            return c = Color.AQUA;
        }
        if (i == 2) {
            return c = Color.BLACK;
        }
        if (i == 3) {
            return c = Color.BLUE;
        }
        if (i == 4) {
            return c = Color.FUCHSIA;
        }
        if (i == 5) {
            return c = Color.GRAY;
        }
        if (i == 6) {
            return c = Color.GREEN;
        }
        if (i == 7) {
            return c = Color.LIME;
        }
        if (i == 8) {
            return c = Color.MAROON;
        }
        if (i == 9) {
            return c = Color.NAVY;
        }
        if (i == 10) {
            return c = Color.OLIVE;
        }
        if (i == 11) {
            return c = Color.ORANGE;
        }
        if (i == 12) {
            return c = Color.PURPLE;
        }
        if (i == 13) {
            return c = Color.RED;
        }
        if (i == 14) {
            return c = Color.SILVER;
        }
        if (i == 15) {
            return c = Color.TEAL;
        }
        if (i == 16) {
            return c = Color.WHITE;
        }
        if (i == 17) {
            return c = Color.YELLOW;
        }
        return null;
    }

    public void summonFireworks(Player player) {
        Firework fw = player.getWorld().spawn(player.getLocation().add(-1, 0, 0), Firework.class);
        FireworkMeta fwm = fw.getFireworkMeta();
        Random r = new Random();
        int rt = r.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;

        if (rt == 1) type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.STAR;
        if (rt == 3) type = FireworkEffect.Type.BURST;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;

        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
        fw.setFireworkMeta(fwm);
    }

    public void save(Player player) {
        StringBuilder p1positions = new StringBuilder();
        StringBuilder p2positions = new StringBuilder();
        for (Integer player1Position : player1Positions) {
            p1positions.append(player1Position).append(" ");
        }
        for (Integer player2Position : player2Positions) {
            p2positions.append(player2Position).append(" ");
        }

        String url = "jdbc:mysql://localhost:3306/tictactoe";
        String uname = "root";
        String password = "password";
        String query = "INSERT INTO games (p1name, p2name, p1positions, p2positions, isFinished)" +
                "VALUES ('" + player1.getDisplayName() + "', '" + player2.getDisplayName() + "', '" + p1positions +
                "', '" + p2positions + "', " + isFinished + ");";
        String queryForID = "SELECT MAX(id) FROM games;";
        String queryForDate = "SELECT date FROM games ORDER BY id DESC LIMIT 1;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            Statement statement1 = con.createStatement();
            Statement statement2 = con.createStatement();
            statement.executeUpdate(query);
            statement1.executeQuery(queryForID);
            statement2.executeQuery(queryForDate);
            ResultSet result1 = statement1.executeQuery(queryForID);
            result1.next();
            int id = Integer.parseInt(result1.getString(1));
            ResultSet result2 = statement2.executeQuery(queryForDate);
            result2.next();
            player.sendMessage("The game has been saved. Your name of the game to load in future : \n" +
                    +id + "_" + player1.getDisplayName() + "_" + player2.getDisplayName() + "_" + result2.getString(1) +
                    "\n Please save or memorize the id of the game for the future loading!");
            GlobalGame.game = null;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveLoaded(Player player) {
        StringBuilder p1positions = new StringBuilder();
        StringBuilder p2positions = new StringBuilder();
        for (Integer player1Position : player1Positions) {
            p1positions.append(player1Position).append(" ");
        }
        for (Integer player2Position : player2Positions) {
            p2positions.append(player2Position).append(" ");
        }

        String url = "jdbc:mysql://localhost:3306/tictactoe";
        String uname = "root";
        String password = "password";
        String query = "UPDATE games SET p1name = '" + player1.getDisplayName() + "'," +
                " p2name =  '" + player2.getDisplayName() + "', p1positions = '" + p1positions +
                "', p2positions = '" + p2positions + "', isFinished = " + isFinished +
                " WHERE id = " + loadedId + ";";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            player.sendMessage("Game saved with the same " + loadedId + " id.");
            GlobalGame.game = null;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void load(int id, Player player) {
        String url = "jdbc:mysql://localhost:3306/tictactoe";
        String uname = "root";
        String password = "password";
        String query = "SELECT * FROM games WHERE id = " + id + ";";
        boolean isEmpty = true;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, uname, password);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                int c = 0;
                if (result.getString(2).equals(player.getDisplayName())) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (result.getString(3).equals(p.getDisplayName())) {
                            player.sendMessage("Game found. Loading...");
                            p.sendMessage("Your previously saved tic tac toe game has been loaded by your friend!\n" +
                                    "You have been teleported to your friend location.");
                            p.teleport(player.getLocation().add(-3, 0, 0));
                            setPlayer2(p);
                            setPlayer1(player);
                            c++;
                            break;
                        }
                    }
                    if (result.getString(7).equals("1")) {
                        player.sendMessage("This game is finished!");
                        GlobalGame.game = null;
                        return;
                    }
                    if (c == 0) {
                        player.sendMessage("Your friend isn't online.\n Inshaallah another time.");
                        return;
                    }
                }
                else if (result.getString(3).equals(player.getDisplayName())) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (result.getString(2).equals(p.getDisplayName())) {
                            player.sendMessage("Game found. Loading...");
                            p.sendMessage("Your previously saved tic tac toe game has been loaded by your friend!\n" +
                                    "You have been teleported to your friend location.");
                            p.teleport(player.getLocation().add(-3, 0, 0));
                            setPlayer1(p);
                            setPlayer2(player);
                            c++;
                            break;
                        }
                    }
                    if (result.getString(7).equals("1")) {
                        player.sendMessage("This game is finished!");
                        GlobalGame.game = null;
                        return;
                    }
                    if (c == 0) {
                        player.sendMessage("Your friend isn't online.\n Inshaallah another time.");
                        return;
                    }
                } else {
                    player.sendMessage("You don't have access to this game.");
                    GlobalGame.game = null;
                    return;
                }
                ArrayList<Integer> p1positions = new ArrayList<>();
                for (int i = 0; i < result.getString(4).length(); i++) {
                    if (result.getString(4).charAt(i) != ' ') {
                        p1positions.add(Integer.parseInt(String.valueOf(result.getString(4).charAt(i))));
                    }
                }
                ArrayList<Integer> p2positions = new ArrayList<>();
                for (int i = 0; i < result.getString(5).length(); i++) {
                    if (result.getString(5).charAt(i) != ' ') {
                        p2positions.add(Integer.parseInt(String.valueOf(result.getString(5).charAt(i))));
                    }
                }
                setPlayer1Positions(p1positions);
                setPlayer2Positions(p2positions);
                if (p1positions.size() == p2positions.size()) {
                    setPlayer1Turn(true);
                    setPlayer2Turn(false);
                    player1.sendMessage("Game successfully loaded!\n It's your turn. Enter a number between 1 - 9.\n" +
                            "You are the blue one :D");
                    player2.sendMessage("Game successfully loaded! Please wait, it's " + player1.getDisplayName() + "'s turn.\n" +
                            "You are the red one :D");
                } else {
                    setPlayer1Turn(false);
                    setPlayer2Turn(true);
                    player2.sendMessage("Game successfully loaded!\n It's your turn. Enter a number between 1 - 9.\n" +
                    "You are the red one :D");
                    player1.sendMessage("Game successfully loaded! Please wait, it's " + player1.getDisplayName() + "'s turn.\n"+
                            "You are the blue one :D");
                }
                int x = player1.getLocation().getBlockX();
                int y = player1.getLocation().getBlockY();
                int z = player1.getLocation().getBlockZ();

                for (int i = -5; i < 5; i++) {
                    for (int j = -5; j < 5; j++) {
                        new Location(player1.getWorld(), x+j, y+10, z+i).getBlock().setType(Material.WHITE_WOOL);
                    }
                }

                player1.teleport(new Location(player1.getWorld(), x, y+11, z));
                player2.teleport(new Location(player1.getWorld(), x+3, y+11, z));
                setGameBoard(player1);

                isLoaded = true;
                loadPlacePiece(p1positions, p2positions);
                loadedId = id;
                isEmpty = false;
            } else {
                player.sendMessage("There is no game with such name!");
                GlobalGame.game = null;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isEmpty) {
            System.out.println("There is no game in database to show!");
        }
    }
}
