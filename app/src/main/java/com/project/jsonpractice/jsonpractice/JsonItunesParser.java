package com.project.jsonpractice.jsonpractice;

import com.project.jsonpractice.jsonpractice.Model.ItunesStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sunil sharma on 9/19/2017.
 */

public class JsonItunesParser
{

    public static ItunesStuff getItunesStuff(String url) throws JSONException
    {
        ItunesStuff itunesStuff = new ItunesStuff();
        JSONObject ituneStuffJsonObject = new JSONObject(url);
        JSONArray resultJsonArray = ituneStuffJsonObject.getJSONArray("results");
        JSONObject artistObject = resultJsonArray.getJSONObject(0);
        itunesStuff.setType(getString("wrapperType", artistObject));
        itunesStuff.setKind(getString("kind" , artistObject));
        itunesStuff.setArtistName(getString("artistName" , artistObject));
        itunesStuff.setCollectionName(getString("collectionName" , artistObject));
        itunesStuff.setArtistViewURL(getString("artworkUrl100" , artistObject));
        itunesStuff.setTrackName(getString("trackName" , artistObject));

        return itunesStuff;
    }


    private static JSONObject getJsonObject(String tagName , JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getJSONObject(tagName);
    }

    private static String getString(String tagName , JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getString(tagName);
    }

    private static float getFlot(String tagName , JSONObject jsonObject) throws JSONException
    {
        return (float) jsonObject.getDouble(tagName);
    }

    private static int getInt(String tagName , JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getInt(tagName);
    }

    private static boolean getBoolean(String tagName , JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getBoolean(tagName);
    }
}
