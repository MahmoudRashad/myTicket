
package com.example.myticket.Model.Network.StadiumModel.Match;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leagues implements Serializable
{

    @SerializedName("cyclic_name")
    @Expose
    private String cyclicName;
    @SerializedName("matches")
    @Expose
    private List<MatchDetails> matches = null;
    private final static long serialVersionUID = 1548109348371701513L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Leagues() {
    }

    /**
     * 
     * @param matches
     * @param cyclicName
     */
    public Leagues(String cyclicName, List<MatchDetails> matches) {
        super();
        this.cyclicName = cyclicName;
        this.matches = matches;
    }

    public String getCyclicName() {
        return cyclicName;
    }

    public void setCyclicName(String cyclicName) {
        this.cyclicName = cyclicName;
    }

    public List<MatchDetails> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchDetails> matches) {
        this.matches = matches;
    }

}
