package jackandfriends.models;

import android.location.Location;

/**
 * Created by chans on 12/3/2017.
 */

public class Event {

    public String title;
    public String description;
    public boolean active;
    public String owner;
    public String location;

    public Event() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Event(String title, String description, boolean active, String owner, String location ) {
        this.title = title;
        this.description = description;
        this.active = active;
        this.owner = owner;
        this.location = location;
    }
}
