/*
 * Copyright (C) 2010 Evgeny Mandrikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sonar.plugins.buildstability;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;

/**
 * TODO comment me
 *
 * @author Evgeny Mandrikov
 */
public class Build {
  public static final String CAUSE_DESCRIPTION_FIELD = "cause_description";
  public static final String CAUSE_USER_FIELD = "cause_user";
  public static final String CAUSE_PROJECT_FIELD = "cause_project";
  public static final String CAUSE_PROJECT_BUILD_FIELD = "cause_build";
  public static final String CAUSE_PROJECT_URL_FIELD = "cause_url";
    
  public static final String URL_FIELD = "url";
  public static final String NUMBER_FIELD = "num";
  public static final String TIMESTAMP_FIELD = "time";
  public static final String DURATION_FIELD = "duration";
  public static final String STABILITY_FIELD = "stability";
  public static final String STATUS_FIELD = "res";
  public static final String DEVELOPERS_FIELD = "dev";

  public static final String STABLE_STATUS = "stable";
  public static final String UNSTABLE_STATUS = "unstable";

  public static final String SUCCESSFUL_STATUS = "ok";
  public static final String FAILED_STATUS = "fail";


    /**
     * Reason the build was executed
     */
    private String causeDescription;

    /**
     * User who executed the build on CI
     */
    private String causeUser;

    /**
     * Project that executed the build
     */
    private String causeProject;

    /**
     * Build id of the project that executed the build
     */
    private String causeProjectBuild;

    /**
     * Url of the project that executed the build
     */
    private String causeProjectUrl;
    

  /**
   * Build URL.
   */
  private String url;

  /**
   * Build number. Required for metrics calculation.
   */
  private Integer number;

  /**
   * Build timestamp. Required for metrics calculation.
   */
  private Long timestamp;

  /**
   * Build result.
   */
  private String result;

  /**
   * True, if build is not successful or unstable. Required for metrics calculation.
   */
  private Boolean stable;

  /**
   * True, if build successfull. Required for metrics calculation.
   */
  private Boolean successful;

  /**
   * Build duration in millisec. Required for metrics calculation.
   * TODO we really need value in milliseconds?
   */
  private Double duration;

  /**
   * TODO comment me, see SONARPLUGINS-482
   */
  private String[] developers;

  public Build(int number, long timestamp, String result,boolean stable, boolean successful, double duration) {
    this.number = number;
    this.timestamp = timestamp;
    this.result = result;
    this.stable = stable;
    this.successful = successful;
    this.duration = duration;
  }

  public Build() {
  }
    
  public String getCauseDescription() {
    return causeDescription;
  }
    
  public Build setCauseDescription(String causeDescription) {
    this.causeDescription = causeDescription;
    return this;
  }
    
  public String getCauseUser() {
    return causeUser;
  }
    
  public Build setCauseUser(String causeUser) {
    this.causeUser = causeUser;
    return this;
  }
    
  public String getCauseProject() {
    return causeProject;
  }
    
  public Build setCauseProject(String causeProject) {
    this.causeProject = causeProject;
    return this;
  }
    
  public String getCauseProjectBuild() {
    return causeProjectBuild;
  }
    
  public Build setCauseProjectBuild(String causeProjectBuild) {
    this.causeProjectBuild = causeProjectBuild;
    return this;
  }
    
  public String getCauseProjectUrl() {
    return causeProjectUrl;
  }

  public Build setCauseProjectUrl(String causeProjectUrl) {
    this.causeProjectUrl = causeProjectUrl;
    return this;
  }
    
  public String getUrl() {
    return url;
  }

  public Build setUrl(String url) {
    this.url = url;
    return this;
  }

  public int getNumber() {
    return number;
  }

  public Build setNumber(int number) {
    this.number = number;
    return this;
  }

  public String getResult() {
    return result;
  }

  public Build setResult(String result) {
    this.result = result;
    return this;
  }
    
  public boolean isStable() {
      return stable;
  }
    
  public Build setStable(boolean stable) {
      this.stable = stable;
      return this;
  }

  public boolean isSuccessful() {
    return successful;
  }

  public Build setSuccessful(boolean successful) {
    this.successful = successful;
    return this;
  }

  public double getDuration() {
    return duration;
  }

  public Build setDuration(double duration) {
    this.duration = duration;
    return this;
  }

  public long getDurationInSeconds() {
    return (long) (duration / 1000);
  }

  public Build setDurationInSeconds(long duration) {
    setDuration(duration * 1000);
    return this;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public Date getDate() {
    return new Date(timestamp);
  }

  public Build setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).
        append(Build.URL_FIELD, url).
        append(Build.CAUSE_DESCRIPTION_FIELD, causeDescription).
        append(Build.CAUSE_USER_FIELD, causeUser).
        append(Build.CAUSE_PROJECT_FIELD, causeProject).
        append(Build.CAUSE_PROJECT_BUILD_FIELD, causeProjectBuild).
        append(Build.CAUSE_PROJECT_URL_FIELD, causeProjectUrl).
        append(Build.NUMBER_FIELD, number).
        append(Build.TIMESTAMP_FIELD, timestamp).
        append(Build.STATUS_FIELD, result).
        append("stable", stable).
        append("successful", successful).
        append(Build.DURATION_FIELD, duration).
        toString();
  }

  public static Build fromString(String data) {
    Build build = new Build();
    String[] fields = StringUtils.split(data, ';');
    for (String field : fields) {
      String key = StringUtils.substringBefore(field, "=");
      String value = StringUtils.substringAfter(field, "=");
      if (URL_FIELD.equalsIgnoreCase(key)) {
        build.setUrl(value);
      } else if (Build.CAUSE_DESCRIPTION_FIELD.equalsIgnoreCase(key)) {
        build.setCauseDescription(value);
      } else if (Build.CAUSE_USER_FIELD.equalsIgnoreCase(key)) {
        build.setCauseUser(value);
      } else if (Build.CAUSE_PROJECT_FIELD.equalsIgnoreCase(key)) {
        build.setCauseProject(value);
      } else if (Build.CAUSE_PROJECT_BUILD_FIELD.equalsIgnoreCase(key)) {
        build.setCauseProjectBuild(value);
      } else if (Build.CAUSE_PROJECT_URL_FIELD.equalsIgnoreCase(key)) {
        build.setCauseProjectUrl(value);
      } else if (Build.NUMBER_FIELD.equalsIgnoreCase(key)) {
        build.setNumber(Integer.parseInt(value));
      } else if (Build.TIMESTAMP_FIELD.equalsIgnoreCase(key)) {
        build.setTimestamp(Long.parseLong(value));
      } else if (Build.DURATION_FIELD.equalsIgnoreCase(key)) {
        build.setDurationInSeconds(Long.parseLong(value));
      } else if (Build.STABILITY_FIELD.equalsIgnoreCase(key)) {
        build.setStable(Build.STABLE_STATUS.equalsIgnoreCase(value));
      } else if (Build.STATUS_FIELD.equalsIgnoreCase(key)) {
        build.setResult(value);
        build.setSuccessful(Build.SUCCESSFUL_STATUS.equalsIgnoreCase(value));
      } else if (Build.DEVELOPERS_FIELD.equalsIgnoreCase(key)) {
        // TODO
      }
    }
    if (build.number == null || build.timestamp == null || build.duration == null || build.successful == null) {
      // TODO error
    }
    return build;
  }

  public String convertToString() {
    StringBuilder sb = new StringBuilder();
    append(sb, Build.URL_FIELD, url); // TODO escape
    append(sb, Build.CAUSE_DESCRIPTION_FIELD, causeDescription);
    append(sb, Build.CAUSE_USER_FIELD, causeUser);
    append(sb, Build.CAUSE_PROJECT_FIELD, causeProject);
    append(sb, Build.CAUSE_PROJECT_BUILD_FIELD, causeProjectBuild);
    append(sb, Build.CAUSE_PROJECT_URL_FIELD, causeProjectUrl);
    append(sb, Build.NUMBER_FIELD, number);
    append(sb, Build.TIMESTAMP_FIELD, timestamp);
    append(sb, Build.DURATION_FIELD, getDurationInSeconds());
    append(sb, Build.STABILITY_FIELD, stable ? Build.STABLE_STATUS : Build.UNSTABLE_STATUS);
    append(sb, Build.STATUS_FIELD, successful ? Build.SUCCESSFUL_STATUS : Build.FAILED_STATUS);
    append(sb, Build.DEVELOPERS_FIELD, StringUtils.join(developers, ','));
    return sb.toString();
  }

  private void append(StringBuilder sb, String field, Object value) {
    if (value == null) {
      return;
    }
    sb.append(field).append('=').append(value).append(';');
  }
}
