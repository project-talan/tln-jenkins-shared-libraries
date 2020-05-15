class paramConstant {
  public static final int TLN_COMPONENT_PARAM_HOST      = 0x00000001;
  public static final int TLN_COMPONENT_PARAM_LSTN      = 0x00000002;
  public static final int TLN_COMPONENT_PARAM_PORT      = 0x00000004;
  public static final int TLN_COMPONENT_PARAM_PORTS     = 0x00000008;
  public static final int TLN_SHARED_COMPONENTS_HOME    = 0x00000010;
  public static final int TLN_PARAMS_COMMON             = TLN_COMPONENT_PARAM_HOST | TLN_COMPONENT_PARAM_LSTN | TLN_COMPONENT_PARAM_PORT | TLN_COMPONENT_PARAM_PORTS | TLN_SHARED_COMPONENTS_HOME;

  public static final int TLN_GITHUB_ACCESS_TOKEN       = 0x00000100;
  public static final int TLN_PARAMS_SCM                = TLN_GITHUB_ACCESS_TOKEN;


  public static final int TLN_SONARQUBE_SERVER          = 0x00001000;
  public static final int TLN_SONARQUBE_SCANNER         = 0x00002000;
  public static final int TLN_SONARQUBE_QUALITY_GATES   = 0x00004000;
  public static final int TLN_SONARQUBE_ACCESS_TOKEN    = 0x00008000;
  public static final int TLN_PARAMS_SONARQUBE          = TLN_SONARQUBE_SERVER | TLN_SONARQUBE_SCANNER | TLN_SONARQUBE_QUALITY_GATES | TLN_SONARQUBE_ACCESS_TOKEN;
}
