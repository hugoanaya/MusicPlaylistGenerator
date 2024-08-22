import java.util.ArrayList;

public class Playlist {
    private ArrayList<Song> playlist;

    public Playlist(){
        this.playlist = new ArrayList<Song>();

    }

    public void addSong(Song song){
        playlist.add(song);
    }

    public int getPlaylistSize(){
        return playlist.size();
    }

    public String getArtist(int index){
        return playlist.get(index).getArtist();
    }

    public String getTitle(int index){
        return playlist.get(index).getTitle();
    }

    public String getLength(int index){
        return playlist.get(index).getLength();
    }

    public void updateArtist(int index, String newArtist){
        playlist.get(index).setArtist(newArtist);
    }

    public void updateTitle(int index, String newTitle){
        playlist.get(index).setTitle(newTitle);
    }

    public void updateLength(int index, String newLength) throws InvalidLengthException{
        if(newLength.matches("(\\d.*):(\\d.*)"))
        {
            playlist.get(index).setLength(newLength);
        }
        else
        {
            throw new InvalidLengthException(newLength);
        }
    }

    public void removeSong(int index){
        playlist.remove(index);
    }

    public void deletePlaylist(){
        if(playlist.isEmpty()){
            System.out.println("Playlist is already empty.");
        } else {
            playlist.clear();
        }
    }

    public void printPlaylist() {
        if(playlist.isEmpty()) {
            System.out.println("Playlist is empty. Add some songs to it!");
        } else {
            for (Song song : playlist){
                song.printSong();
            }
        }
    }
}
