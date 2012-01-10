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

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.Arrays;
import java.util.List;

/**
 * @author Evgeny Mandrikov
 */
public class BuildStabilityMetrics implements Metrics {
  public static final String DOMAIN_BUILD = "Continuous integration";

    /**
     * Description of the build.
     */
    public static final Metric CAUSE_DESCRIPTION = new Metric(
            "cause_description",
            "Build description",
            "Description of build",
            Metric.ValueType.STRING,
            Metric.DIRECTION_NONE,
            false,
            DOMAIN_BUILD
    );

    /**
     * User who executed the build.
     */
    public static final Metric CAUSE_USER = new Metric(
            "cause_user",
            "User initiator",
            "Initiating user of the build",
            Metric.ValueType.STRING,
            Metric.DIRECTION_NONE,
            false,
            DOMAIN_BUILD
    );

    /**
     * Project that initiated this build
     */
    public static final Metric CAUSE_PROJECT = new Metric(
            "cause_project",
            "Project initiator",
            "Initiating project of the build",
            Metric.ValueType.STRING,
            Metric.DIRECTION_NONE,
            false,
            DOMAIN_BUILD
    );

    /**
     * Build number of the build that initiated this build
     */
    public static final Metric CAUSE_PROJECT_BUILD = new Metric(
            "cause_project_build",
            "Project build initiator",
            "Initiating project build of the build",
            Metric.ValueType.STRING,
            Metric.DIRECTION_NONE,
            false,
            DOMAIN_BUILD
    );

    /**
     * URL of the project build that initiated this build.
     */
    public static final Metric CAUSE_PROJECT_URL = new Metric(
            "cause_project_url",
            "Project url initiator",
            "Initiating project url of the build",
            Metric.ValueType.STRING,
            Metric.DIRECTION_NONE,
            false,
            DOMAIN_BUILD
    );

    /**
     * URL of the build of the project
     */
    public static final Metric URL = new Metric(
            "build_url",
            "Build url",
            "URL of the build in CI",
            Metric.ValueType.STRING,
            Metric.DIRECTION_NONE,
            false,
            DOMAIN_BUILD
    );

  /**
   * Number of builds.
   */
  public static final Metric BUILDS = new Metric(
      "builds",
      "Builds",
      "Number of builds",
      Metric.ValueType.INT,
      Metric.DIRECTION_NONE,
      false,
      DOMAIN_BUILD
  );

  /**
   * Number of failed builds.
   */
  public static final Metric FAILED = new Metric(
      "build_failures",
      "Failed Builds",
      "Number of failed builds",
      Metric.ValueType.INT,
      Metric.DIRECTION_WORST,
      false,
      DOMAIN_BUILD
  );

  /**
   * Number of unstable builds.
   */
  public static final Metric UNSTABLE = new Metric(
          "build_unstables",
          "Unstable Builds",
          "Number of unstable builds",
          Metric.ValueType.INT,
          Metric.DIRECTION_WORST,
          false,
          DOMAIN_BUILD
  );

  /**
   * Ratio of successful builds. Measured as percentage of successful builds out of all last builds.
   */
  public static final Metric SUCCESS_RATE = new Metric(
      "build_success_density",
      "Success Rate (%)",
      "Ratio of successful builds",
      Metric.ValueType.PERCENT,
      Metric.DIRECTION_BETTER,
      false,
      DOMAIN_BUILD
  );

  /**
   * Build average duration. Includes only duration of successful builds.
   */
  public static final Metric AVG_DURATION = new Metric(
      "build_average_duration",
      "Average Duration",
      "Average Duration",
      Metric.ValueType.MILLISEC,
      Metric.DIRECTION_WORST,
      false,
      DOMAIN_BUILD
  );

  /**
   * Duration of longest successful build.
   */
  public static final Metric LONGEST_DURATION = new Metric(
      "build_longest_duration",
      "Longest duration",
      "Duration of longest successful build",
      Metric.ValueType.MILLISEC,
      Metric.DIRECTION_WORST,
      false,
      DOMAIN_BUILD
  );

  /**
   * Duration of shortest successful build.
   */
  public static final Metric SHORTEST_DURATION = new Metric(
      "build_shortest_duration",
      "Shortest duration",
      "Duration of shortest successful build",
      Metric.ValueType.MILLISEC,
      Metric.DIRECTION_WORST,
      false,
      DOMAIN_BUILD
  );

  /**
   * Average time to fix a failure.
   */
  public static final Metric AVG_TIME_TO_FIX = new Metric(
      "build_average_time_to_fix_failure",
      "Average time to fix a failure",
      "Average time to fix a failure",
      Metric.ValueType.MILLISEC,
      Metric.DIRECTION_WORST,
      false,
      DOMAIN_BUILD
  );

  /**
   * Longest time to fix a failure.
   */
  public static final Metric LONGEST_TIME_TO_FIX = new Metric(
      "build_longest_time_to_fix_failure",
      "Longest time to fix a failure",
      "Longest time to fix a failure",
      Metric.ValueType.MILLISEC,
      Metric.DIRECTION_WORST,
      false,
      DOMAIN_BUILD
  );

  /**
   * Average number of builds between fixes.
   */
  public static final Metric AVG_BUILDS_TO_FIX = new Metric(
      "build_average_builds_to_fix_failure",
      "Average number of builds between fixes",
      "Average number of builds between fixes",
      Metric.ValueType.INT,
      Metric.DIRECTION_WORST,
      false,
      DOMAIN_BUILD
  );

    /**
     * Average time to fix a stability issue.
     */
    public static final Metric AVG_TIME_TO_STABILIZE = new Metric(
            "build_average_time_to_stabilize",
            "Average time to fix a stability issue",
            "Average time to fix a stability issue",
            Metric.ValueType.MILLISEC,
            Metric.DIRECTION_WORST,
            false,
            DOMAIN_BUILD
    );

    /**
     * Longest time to fix a stability issue.
     */
    public static final Metric LONGEST_TIME_TO_STABILIZE = new Metric(
            "build_longest_time_to_stabilize",
            "Longest time to fix a stability issue",
            "Longest time to fix a stability issue",
            Metric.ValueType.MILLISEC,
            Metric.DIRECTION_WORST,
            false,
            DOMAIN_BUILD
    );

    /**
     * Average number of builds between stable builds.
     */
    public static final Metric AVG_BUILDS_TO_STABILIZE = new Metric(
            "build_average_builds_to_stabilize",
            "Average number of builds between stable builds",
            "Average number of builds between stable builds",
            Metric.ValueType.INT,
            Metric.DIRECTION_WORST,
            false,
            DOMAIN_BUILD
    );

  /**
   * TODO comment me (seconds)
   */
  public static final Metric DURATIONS = new Metric(
      "build_durations",
      "Durations",
      "Durations",
      Metric.ValueType.DATA,
      Metric.DIRECTION_NONE,
      false,
      DOMAIN_BUILD
  );

  /**
   * TODO comment me
   */
  public static final Metric RESULTS = new Metric(
      "build_results",
      "Results",
      "Results",
      Metric.ValueType.DATA,
      Metric.DIRECTION_NONE,
      false,
      DOMAIN_BUILD
  );

  public List<Metric> getMetrics() {
    return Arrays.asList(
        CAUSE_DESCRIPTION,
        CAUSE_USER,
        CAUSE_PROJECT,
        CAUSE_PROJECT_BUILD,
        CAUSE_PROJECT_URL,

        URL,

        BUILDS,
        FAILED,
        UNSTABLE,
        SUCCESS_RATE,

        AVG_DURATION,
        LONGEST_DURATION,
        SHORTEST_DURATION,

        AVG_TIME_TO_FIX,
        LONGEST_TIME_TO_FIX,
        AVG_BUILDS_TO_FIX,

        AVG_TIME_TO_STABILIZE,
        LONGEST_TIME_TO_STABILIZE,
        AVG_BUILDS_TO_STABILIZE,

        DURATIONS,
        RESULTS
    );
  }
}
