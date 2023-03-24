package org.insbaixcamp.animelist.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.insbaixcamp.animelist.R;
import org.insbaixcamp.animelist.adapters.AnimeListAdapter;
import org.insbaixcamp.animelist.databinding.FragmentGalleryBinding;
import org.insbaixcamp.animelist.dataclass.DataAnime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    private FragmentGalleryBinding binding;
    ArrayList<DataAnime> arrayListAnime = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://api.jikan.moe/v4/top/anime";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, this, this);
        requestQueue.add(jsonObjectRequest);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("RESPONSICION", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("RESPONSICION", response.toString());
        arrayListAnime.clear();
        try {
            JSONArray jsonArrayImage = response.getJSONArray("data");
            for(int i = 0; i < jsonArrayImage.length(); i++){
                JSONObject data = jsonArrayImage.getJSONObject(i);
                JSONObject jpg = data.getJSONObject("images").getJSONObject("jpg");
                String imageUrl = jpg.getString("large_image_url");
                String title = data.getString("title");

                DataAnime anime = new DataAnime(imageUrl, title);
                arrayListAnime.add(anime);
            }

            AnimeListAdapter adapter = new AnimeListAdapter(arrayListAnime, getContext());
            binding.rvAnimeList.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            binding.rvAnimeList.setLayoutManager(manager);

        } catch (JSONException e) {
            Log.d("RESPONSICION", e.toString());
        }
    }
}