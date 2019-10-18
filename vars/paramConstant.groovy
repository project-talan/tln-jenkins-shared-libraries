class paramConstant {
  public static final int COMPONENT_PARAM_HOST    = 0x00000001;
  public static final int COMPONENT_PARAM_LSTN    = 0x00000002;
  public static final int COMPONENT_PARAM_PORT    = 0x00000004;
  public static final int COMPONENT_PARAM_PORTS   = 0x00000008;
  public static final int TALAN_PRESETS_PATH      = 0x00000010;
  public static final int SONARQUBE_SERVER        = 0x00000020;
  public static final int SONARQUBE_SCANNER       = 0x00000040;
  public static final int SONARQUBE_QUALITY_GATES = 0x00000080;
  public static final int SONARQUBE_ACCESS_TOKEN  = 0x00000100;
  public static final int GITHUB_ACCESS_TOKEN     = 0x00000200;
  public static final int PARAMS_COMMON           = COMPONENT_PARAM_HOST | COMPONENT_PARAM_LSTN | COMPONENT_PARAM_PORT | COMPONENT_PARAM_PORTS | TALAN_PRESETS_PATH | SONARQUBE_SERVER | SONARQUBE_SCANNER | SONARQUBE_QUALITY_GATES | SONARQUBE_ACCESS_TOKEN | GITHUB_ACCESS_TOKEN;
}
