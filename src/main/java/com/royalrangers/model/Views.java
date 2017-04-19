package com.royalrangers.model;

public class Views {

    public interface Common {}
    public interface Profile extends Common{}
    public interface Public extends Profile{}
    public interface Internal extends Profile {}
}
