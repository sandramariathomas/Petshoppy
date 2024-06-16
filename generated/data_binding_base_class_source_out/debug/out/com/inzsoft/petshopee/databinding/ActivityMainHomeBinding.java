// Generated by view binder compiler. Do not edit!
package com.inzsoft.petshopee.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.inzsoft.petshopee.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView cmpAdrs;

  @NonNull
  public final TextView cmpEmail;

  @NonNull
  public final TextView cmpMob;

  @NonNull
  public final TextView cmpName;

  @NonNull
  public final FrameLayout frameLayout;

  @NonNull
  public final FrameLayout frameLayout2;

  @NonNull
  public final GridView gridViewImageText;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final TextView logoutbtn;

  @NonNull
  public final TextView offid;

  @NonNull
  public final TextView offname;

  @NonNull
  public final TextView textView16;

  private ActivityMainHomeBinding(@NonNull ConstraintLayout rootView, @NonNull TextView cmpAdrs,
      @NonNull TextView cmpEmail, @NonNull TextView cmpMob, @NonNull TextView cmpName,
      @NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2,
      @NonNull GridView gridViewImageText, @NonNull ImageView imageView7,
      @NonNull TextView logoutbtn, @NonNull TextView offid, @NonNull TextView offname,
      @NonNull TextView textView16) {
    this.rootView = rootView;
    this.cmpAdrs = cmpAdrs;
    this.cmpEmail = cmpEmail;
    this.cmpMob = cmpMob;
    this.cmpName = cmpName;
    this.frameLayout = frameLayout;
    this.frameLayout2 = frameLayout2;
    this.gridViewImageText = gridViewImageText;
    this.imageView7 = imageView7;
    this.logoutbtn = logoutbtn;
    this.offid = offid;
    this.offname = offname;
    this.textView16 = textView16;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cmp_adrs;
      TextView cmpAdrs = ViewBindings.findChildViewById(rootView, id);
      if (cmpAdrs == null) {
        break missingId;
      }

      id = R.id.cmp_email;
      TextView cmpEmail = ViewBindings.findChildViewById(rootView, id);
      if (cmpEmail == null) {
        break missingId;
      }

      id = R.id.cmp_mob;
      TextView cmpMob = ViewBindings.findChildViewById(rootView, id);
      if (cmpMob == null) {
        break missingId;
      }

      id = R.id.cmp_name;
      TextView cmpName = ViewBindings.findChildViewById(rootView, id);
      if (cmpName == null) {
        break missingId;
      }

      id = R.id.frameLayout;
      FrameLayout frameLayout = ViewBindings.findChildViewById(rootView, id);
      if (frameLayout == null) {
        break missingId;
      }

      id = R.id.frameLayout2;
      FrameLayout frameLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (frameLayout2 == null) {
        break missingId;
      }

      id = R.id.grid_view_image_text;
      GridView gridViewImageText = ViewBindings.findChildViewById(rootView, id);
      if (gridViewImageText == null) {
        break missingId;
      }

      id = R.id.imageView7;
      ImageView imageView7 = ViewBindings.findChildViewById(rootView, id);
      if (imageView7 == null) {
        break missingId;
      }

      id = R.id.logoutbtn;
      TextView logoutbtn = ViewBindings.findChildViewById(rootView, id);
      if (logoutbtn == null) {
        break missingId;
      }

      id = R.id.offid;
      TextView offid = ViewBindings.findChildViewById(rootView, id);
      if (offid == null) {
        break missingId;
      }

      id = R.id.offname;
      TextView offname = ViewBindings.findChildViewById(rootView, id);
      if (offname == null) {
        break missingId;
      }

      id = R.id.textView16;
      TextView textView16 = ViewBindings.findChildViewById(rootView, id);
      if (textView16 == null) {
        break missingId;
      }

      return new ActivityMainHomeBinding((ConstraintLayout) rootView, cmpAdrs, cmpEmail, cmpMob,
          cmpName, frameLayout, frameLayout2, gridViewImageText, imageView7, logoutbtn, offid,
          offname, textView16);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}