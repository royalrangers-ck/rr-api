package com.royalrangers.model;

public class Views {

    public interface Common {}
    public interface AchievementProfile extends Common {}
    public interface Profile extends AchievementProfile {}
    public interface Public extends Profile{}
    public interface Internal extends Profile {}
}
