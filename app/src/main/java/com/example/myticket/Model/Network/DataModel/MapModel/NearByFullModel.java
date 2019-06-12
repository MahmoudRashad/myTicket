package com.example.myticket.Model.Network.DataModel.MapModel;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearByFullModel {

        @SerializedName("html_attributions")
        @Expose
        private List<Object> htmlAttributions = null;
        @SerializedName("next_page_token")
        @Expose
        private String nextPageToken;
        @SerializedName("results")
        @Expose
        private List<Result> results = null;
        @SerializedName("status")
        @Expose
        private String status;

        /**
         * No args constructor for use in serialization
         *
         */
        public NearByFullModel() {
        }

        /**
         *
         * @param results
         * @param status
         * @param nextPageToken
         * @param htmlAttributions
         */
        public NearByFullModel(List<Object> htmlAttributions, String nextPageToken, List<Result> results, String status) {
            super();
            this.htmlAttributions = htmlAttributions;
            this.nextPageToken = nextPageToken;
            this.results = results;
            this.status = status;
        }

        public List<Object> getHtmlAttributions() {
            return htmlAttributions;
        }

        public void setHtmlAttributions(List<Object> htmlAttributions) {
            this.htmlAttributions = htmlAttributions;
        }

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
