
//Each individual Track object contains a String for its name, a soundId, a streamId, and
//some member functions. You don't need to deal with track object internals except to create
//them in order to initialize them to add them to the allTracks list (explained ahead), and
//to read their name with getName(). Otherwise you just pass Track objects to the TrackList
//with addTrack, remove them by index, etc. TrackList handles the playing of the tracks by
//asking each track individually to play itself.

//this is an object containing the list of Tracks which have been currently added to the song.
//this object contains an ArrayList<Track> and has some member functions.
//to add a Track to the song call public void addTrack(Track t);
TrackList list = new TrackList();

//this is the list of Tracks which are available in the program
//populate this array with a Track for each sound file available at initialization
ArrayList<Track> allTracks;
//The "add tracks" page needs to iterate through this array in order to create
//a button for each Track. (The name of the track can be accessed with void Track::getName();
//Each such button on the add tracks page, when clicked, should call:
list.addTrack(~~~);
//with its corresponding track in the parentheses.

//On the main page, when the play button is pressed, call:
list.playAll();
//Likewise, to pause or stop, call:
list.pauseAll();
list.stopAll();

//The main page should generate the list of tracks currently in the song by iterating thru
//list and creating an element for each track in the list. To remove the i'th track from the
//TrackList, call:
list.removeTrack(i);
//Use this function to implement the removal of a track from the song

//In order to save/load the song, save the TrackList object to persistent memory.
//Not sure if more needs to be added to the class internally in order to get this done.
//The TrackList object contains all the relevant information for an individual song needed to save or load


//SUMMARY:
//You need to create two objects:
//1) An array of Tracks to represent all possible tracks available for the program.
//		This array should be populated with a track for each sound file we have
//		chosen to include in the program. This object won't change after initialization.
//2) A TrackList object, initially empty.

//- Populate the buttons on the "add track" screen by iterating through the allTracks array.
//- Populate the beat editor on the main screen by iterating through TrackList's array
//- In order to add beats to the editor, get the beat from allTracks and call TrackList.addTrack(..) with it,
//		and then re-draw the main screen by iterating thru TrackList.
//- In order to remove beats from the beat editor, have the X button for a track call TrackList.removeTrack(..)
//		with its index, and then re-draw the main screen by iterating thru TrackList.
//- In order to play/pause/stop, simply call TrackList.playAll(), etc.