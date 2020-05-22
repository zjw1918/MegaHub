package io.mega.megahub.andfix;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

import io.mega.megahub.Utils;

/**
 * andfix 包装api
 * 单例
 */
public class AndFixPatchManager {
    private PatchManager patchManager;

    private AndFixPatchManager() {}
    private static class Inner {
        static AndFixPatchManager ins = new AndFixPatchManager();
    }

    public static AndFixPatchManager getInstance() {
        return Inner.ins;
    }

    // load patch
    public void initPatch(Context context) {
        patchManager = new PatchManager(context);
        patchManager.init(Utils.getVersion(context));
        patchManager.loadPatch();
    }

    public void addPatch(String path) {
        if (patchManager == null) return;
        try {
            patchManager.addPatch(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
