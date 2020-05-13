package org.semenov.votingrest.web;

public interface HasIdAndEmail extends HasId {
    String getEmail();
}