package com.example.edulex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


public class humanbody_system extends AppCompatActivity {
    private ArFragment skeleton;
    private ModelRenderable modelRenderable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humanbody_system);



        skeleton = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.skeleton);
        setupmodel();
        setupplane();
    }
    private void setupplane() {
        skeleton.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(skeleton.getArSceneView().getScene());
                createModel(anchorNode);
            }
        });
    }

    private void setupmodel() {
        ModelRenderable.builder()
               .setSource(this,R.raw.scene)
                .build()
                .thenAccept(renderable ->modelRenderable =renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(humanbody_system.this,"Model can't be loaded",Toast.LENGTH_LONG).show();
                    return null;
                });
    }

    private void createModel(AnchorNode anchorNode) {
        TransformableNode node = new TransformableNode(skeleton.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
    }
}
