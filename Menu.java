import java.io.*;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Playlist playlist = new Playlist();

    //Display a menu, prompts for user input (int selection), and returns the input.
    public int showMenu(){
        int selection;

        System.out.print("\nWhat would you like to select?"
        + "\n1 - Add song"+"\n2 - View playlist"
        + "\n3 - Update song" + "\n4 - Remove song" + "\n5 - Delete playlist"
        + " \n6 - Exit" + "\nSelection: ");

        selection = scanner.nextInt();
        System.out.print("\n");

        return selection;
    }

    //Displays menu from showMenu(): Contains a switch,
    //executes case based on returned into from showMenu().
    public void runMenu(){

        int selection = showMenu();

        switch (selection){
            case 1:
                addSong();
                savePlaylist();
                runMenu();
                break;
            case 2:
                viewPlaylist();
                runMenu();
                break;
            case 3:
                updateSong();
                savePlaylist();
                runMenu();
                break;
            case 4:
                removeSong();
                savePlaylist();
                runMenu();
                break;
            case 5:
                playlist.deletePlaylist();
                runMenu();
            case 6:
                savePlaylist();
                System.exit(0);
                break;
            default:
                System.out.println("\nThat is not a valid selection!\n");
                runMenu();
        }
    }

    //Creates a new Song with user inputting the following information:
    //artist, title, song length. Adds Song to a Playlist.
    public void addSong(){
        Song newSong = new Song(null, null, "0:00");

        newSong.setArtist(scanner.nextLine());
        System.out.print("Enter Artist: ");
        newSong.setArtist((scanner.nextLine()));
        System.out.print("Enter Title: ");
        newSong.setTitle(scanner.nextLine());
        do {
            try{
                System.out.print("Enter Length: ");
                newSong.setLength(scanner.next());
            }
            catch (InvalidLengthException invalidLengthException){
                System.out.print(invalidLengthException.toString());
                System.out.print("Enter length as \"minutes:seconds\".\n");
            }
        }
        while (newSong.getLength() == null);

        playlist.addSong(newSong);
    }

    //Formats and displays all Songs in Playlist.
    public void viewPlaylist(){
        if(playlist.getPlaylistSize() == 0){
            System.out.println("\nPlaylist empty!");
        }
        else {
            for (int i = 0; i < playlist.getPlaylistSize(); i++){
                System.out.print("\n#" + (i+1) + " ");
                System.out.print(""+ playlist.getArtist(i) + " - ");
                System.out.print("\"" +playlist.getTitle(i) + "\"" + ", ");
                System.out.print(playlist.getLength(i));
            }
        }
        System.out.print("\n");
    }

    //Displays Playlist; prompts user to select song,
    //asks which variable of song they would like to update,
    //asks for input for that variable to update
    public void updateSong(){
        if(playlist.getPlaylistSize() == 0){
            System.out.println("\nPlaylist empty!");
        }
        else {
            viewPlaylist();
            System.out.print("\nPlease select a song to update: ");
            int songSelection = scanner.nextInt();

            System.out.print("\nWhat would you like to update?\n" + "1 - Artist\n" + "2 - Title\n" + "3 - Length\n" + "Selection: ");

            int updateSelection = scanner.nextInt();

            switch (updateSelection){
                case 1:
                    System.out.print("Enter Artist: ");
                    String artist = scanner.next();
                    playlist.updateArtist(songSelection, artist);
                    break;
                case 2:
                    System.out.print("Enter Title: ");
                    String title = scanner.next();
                    playlist.updateTitle(songSelection, title);
                    break;
                case 3:
                    boolean lengthFlag = false;
                    do {
                        try {
                            System.out.print("Enter Length: ");
                            String length = scanner.next();
                            playlist.updateLength(songSelection, length);
                            lengthFlag = true;
                        }
                        catch (InvalidLengthException invalidLength){
                            System.out.print(invalidLength.toString());
                            System.out.print("Enter length as \"minutes:seconds\".\n");
                        }
                    } while (!lengthFlag);
                    break;
                default:
                    System.out.print("\nThat is not a valid selection!\n");
                    runMenu();
            }
        }
    }

    //Displays Playlist: prompts user to select Song to delete and removes it from Playlist.

    public void removeSong(){
        if (playlist.getPlaylistSize() == 0){
            System.out.print("\nPlaylist is empty!");
        }
        else {
            viewPlaylist();
            boolean flag = false;
            do {
                System.out.print("\nSelect a song to remove: ");
                int removeSelection = scanner.nextInt();

                if(removeSelection < playlist.getPlaylistSize() || removeSelection > playlist.getPlaylistSize()){
                    System.out.print("That is not a valid selection!\n");
                }
                else {
                    playlist.removeSong(--removeSelection);
                    flag = true;
                }
            } while (!flag);
        }
    }

    //Write the contents of Playlist to a file called playlist.
    public void savePlaylist(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("playlist");

            ObjectOutputStream outObjectStream = new ObjectOutputStream(fileOutputStream);

            outObjectStream.writeObject(playlist);

            outObjectStream.flush();
            outObjectStream.close();
        }
        catch(FileNotFoundException fnfException){
            System.out.println("No file");
        }
        catch(IOException ioException){
            System.out.println("Bad IO");
        }
    }
    public void loadPlaylist(){
        try {
            FileInputStream fileInputStream = new FileInputStream("playlist");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            playlist = (Playlist)objectInputStream.readObject();

            objectInputStream.close();
        }
        catch (FileNotFoundException fnfException){
            System.out.println("No File");
        }
        catch (IOException ioException){
            System.out.println("IO no good");
        }
        catch (ClassNotFoundException cnfException){
            System.out.println("This is not a Playlist.");
        }
    }

}
