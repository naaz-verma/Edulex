package com.example.edulex;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class solar_system extends AppCompatActivity {



    private ModelAnimator modelAnimator;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system);

       ArFragment solar = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.solar);
       solar.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
           createModel(hitResult.createAnchor(),solar);
       });



    }

    private void createModel(Anchor anchor, ArFragment solar) {
        ModelRenderable
                .builder()
                .setSource(this,Uri.parse("solar system.sfb"))
                .build()
                .thenAccept(modelRenderable -> {
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    SkeletonNode skeletonNode = new SkeletonNode();
                    skeletonNode.setParent(anchorNode);
                    skeletonNode.setRenderable(modelRenderable);
                    solar.getArSceneView().getScene().addChild(anchorNode);

                    Button animate = (Button)findViewById(R.id.animate);
                    animate.setOnClickListener(view -> animateModel(modelRenderable));
                })
                .exceptionally(throwable -> {
                    Toast.makeText(solar_system.this,"Model can't be loaded",Toast.LENGTH_LONG).show();
                    return null;
                });
    }

    private void animateModel(ModelRenderable modelRenderable) {
        if(modelAnimator!=null && modelAnimator.isRunning())
            modelAnimator.end();

        int ani_count=modelRenderable.getAnimationDataCount();

        if(i==ani_count)
            i=0;

        AnimationData animationData = modelRenderable.getAnimationData(i);

        modelAnimator = new ModelAnimator(animationData,modelRenderable);
        modelAnimator.start();
        i++;
    }
}