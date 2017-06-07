package de.wolff.sample;

/**
 * Created by luis- on 05.06.2017.
 */
public class Viewable {

    static final Object NO_MODEL = new Object();

    private final String path;
    private final Object model;

    Viewable(String path){
        this.path = path;
        this.model = NO_MODEL;
    }

    Viewable(String path, Object model){
        this.path = path;
        this.model = model;
    }

    public String getPath() {
        return path;
    }

    public Object getModel() {
        return model;
    }
}
