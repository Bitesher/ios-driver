/*
 * Copyright 2012-2013 eBay Software Foundation and ios-driver committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.uiautomation.ios.server.command.uiautomation;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.Response;
import org.uiautomation.ios.UIAModels.Orientation;
import org.uiautomation.ios.communication.WebDriverLikeRequest;
import org.uiautomation.ios.server.IOSServerManager;
import org.uiautomation.ios.server.command.UIAScriptHandler;
import org.uiautomation.ios.server.utils.JSTemplate;

public class SetOrientationNHandler extends UIAScriptHandler {

  private static final JSTemplate template = new JSTemplate(
      "UIATarget.localTarget().setDeviceOrientation(%:orientation$s);" +
      "UIAutomation.createJSONResponse('%:sessionId$s',0,'')",
      "sessionId", "orientation");

  public SetOrientationNHandler(IOSServerManager driver, WebDriverLikeRequest request) {
    super(driver, request);

    JSONObject payload = request.getPayload();
    String orientation = payload.optString("orientation");
    Orientation o = Orientation.valueOf(orientation);
    String js = template.generate(
        request.getSession(),
        o.instrumentsValue());
    setJS(js);
  }

  @Override
  public Response handle() throws Exception {
    Response r = super.handle();
    return r;
  }

  @Override
  public JSONObject configurationDescription() throws JSONException {
    return noConfigDefined();
  }
}