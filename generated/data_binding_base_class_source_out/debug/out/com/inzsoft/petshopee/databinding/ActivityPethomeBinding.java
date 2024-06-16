// Generated by view binder compiler. Do not edit!
package com.inzsoft.petshopee.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.inzsoft.petshopee.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPethomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FrameLayout frameLayout2;

  @NonNull
  public final GridView idGVCourses;

  private ActivityPethomeBinding(@NonNull ConstraintLayout rootView,
      @NonNull FrameLayout frameLayout2, @NonNull GridView idGVCourses) {
    this.rootView = rootView;
    this.frameLayout2 = frameLayout2;
    this.idGVCourses = idGVCourses;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPethomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPethomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_pethome, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPethomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.frameLayout2;
      FrameLayout frameLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (frameLayout2 == null) {
        break missingId;
      }

      id = R.id.idGVCourses;
      GridView idGVCourses = ViewBindings.findChildViewById(rootView, id);
      if (idGVCourses == null) {
        break missingId;
      }

      return new ActivityPethomeBinding((ConstraintLayout) rootView, frameLayout2, idGVCourses);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
