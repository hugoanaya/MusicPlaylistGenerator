public class Song {
    private String title;
    private String artist;
    private String length;

    public Song(String title, String artist, String length){
        this.title = title;
        this.artist = artist;
        if(length.matches("(\\d.*):(\\d.*)"))
        {
            this.length = length;
        }
        else
        {
            System.out.println("Enter a valid time stamp.");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) throws InvalidLengthException {
        if(length.matches("(\\d.*):(\\d.*)"))
        {
            this.length = length;
        }
        else
        {
            throw new InvalidLengthException(length);
        }
    }

    public static void printSong(String title, String artist, String length){
        System.out.printf("%20s: %6s %1s%n", title, artist, length);
    }

    public void printSong(){
        printSong(getTitle().toUpperCase(), getArtist(), getLength());
    }
}
