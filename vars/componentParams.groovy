def getCommonParameters(mask, defaults = null) {
  def result = []
  def params = [
    ['type': 'string',  'name': 'COMPONENT_PARAM_HOST',    'flag': paramConstant.COMPONENT_PARAM_HOST,     'default': 'localhost' ],
    ['type': 'string',  'name': 'COMPONENT_PARAM_LSTN',    'flag': paramConstant.COMPONENT_PARAM_LSTN,     'default': '0.0.0.0' ],
    ['type': 'string',  'name': 'COMPONENT_PARAM_PORT',    'flag': paramConstant.COMPONENT_PARAM_PORT,     'default': '9080' ],
    ['type': 'string',  'name': 'COMPONENT_PARAM_PORTS',   'flag': paramConstant.COMPONENT_PARAM_PORTS,    'default': '9443' ],
    ['type': 'string',  'name': 'TALAN_PRESETS_PATH',      'flag': paramConstant.TALAN_PRESETS_PATH,       'default': '/tmp' ],
    ['type': 'string',  'name': 'SONARQUBE_SERVER',        'flag': paramConstant.SONARQUBE_SERVER,         'default': '' ],
    ['type': 'string',  'name': 'SONARQUBE_SCANNER',       'flag': paramConstant.SONARQUBE_SCANNER,        'default': '' ],
    ['type': 'boolean', 'name': 'SONARQUBE_QUALITY_GATES', 'flag': paramConstant.SONARQUBE_QUALITY_GATES,  'default': '' ],
    ['type': 'string',  'name': 'SONARQUBE_ACCESS_TOKEN',  'flag': paramConstant.SONARQUBE_ACCESS_TOKEN,   'default': '' ],
    ['type': 'string',  'name': 'GITHUB_ACCESS_TOKEN',     'flag': paramConstant.GITHUB_ACCESS_TOKEN,      'default': '' ]
  ]
  params.each {
    if ((mask & it.flag) == it.flag) {
      if (it.type == 'string') {
        result += [string(name: it.name, defaultValue: (defaults && defaults[it.name])?defaults[it.name]:it.default)]
      } else if (it.type == 'text') {
        result += [text(name: it.name, defaultValue: (defaults && defaults[it.name])?defaults[it.name].join('\n'):it.default)]
      } else if (it.type == 'boolean') {
        result += [booleanParam(name: it.name, defaultValue: (defaults && defaults[it.name])?defaults[it.name]:it.default)]
      }
    }
  }
  return result
}

