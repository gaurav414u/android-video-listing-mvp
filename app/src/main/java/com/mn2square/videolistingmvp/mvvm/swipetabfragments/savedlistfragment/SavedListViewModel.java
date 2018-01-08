package com.mn2square.videolistingmvp.mvvm.swipetabfragments.savedlistfragment;

import com.mn2square.videolistingmvp.mvvm.pojo.VideoListInfo;
import com.mn2square.videolistingmvp.mvvm.repository.VideoListRepository.VideoListRepositoryImpl;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by nitinagarwal on 3/13/17.
 */

public class SavedListViewModel extends AndroidViewModel {
    public static final String TAG = "SaveListViewModel";
    private static final String SORT_TYPE_PREFERENCE_KEY = "sort_type";

    MediatorLiveData<VideoListInfo> mVideoListInfoLiveData;
    VideoListRepositoryImpl mVideoListRepositoryImpl;

    public SavedListViewModel(@NonNull Application application) {
        super(application);
        mVideoListInfoLiveData = new MediatorLiveData<>();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(application);
        mVideoListRepositoryImpl = VideoListRepositoryImpl.getInstance(application, settings.getInt(SORT_TYPE_PREFERENCE_KEY, 3));

        mVideoListInfoLiveData.addSource(mVideoListRepositoryImpl.getVideoListInfoLiveData(), new Observer<VideoListInfo>() {
            @Override
            public void onChanged(@Nullable VideoListInfo videoListInfo) {
                Log.d(TAG, "Mediator livev data");
                mVideoListInfoLiveData.setValue(videoListInfo);
            }
        });
    }
}
