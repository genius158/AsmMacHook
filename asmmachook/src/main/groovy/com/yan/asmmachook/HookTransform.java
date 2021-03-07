package com.yan.asmmachook;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.quinn.hunter.transform.HunterTransform;
import java.io.IOException;
import java.util.Collection;
import org.gradle.api.Project;

public final class HookTransform extends HunterTransform {
  private HookExtension hookExtension;

  public HookTransform(Project project) {
    super(project);
    hookExtension = project.getExtensions().create("gpAnrExt", HookExtension.class);

    this.bytecodeWeaver = new HookWeaver(hookExtension);
    HookLog.info("HookExtension:" + hookExtension.toString());
  }

  @Override
  public void transform(Context context, Collection<TransformInput> inputs,
      Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider,
      boolean isIncremental) throws IOException, TransformException, InterruptedException {
      bytecodeWeaver.setExtension(hookExtension);

    HookLog.info("HookExtension:" + hookExtension.toString());
    super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
  }
}
