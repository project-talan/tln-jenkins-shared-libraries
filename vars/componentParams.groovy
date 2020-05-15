def getCommonParameters(mask, defaults = null) {
  def result = []
  def params = [
    ['type': 'string',  'name': 'TLN_COMPONENT_PARAM_HOST',   'flag': paramConstant.TLN_COMPONENT_PARAM_HOST,     'default': 'localhost' ],
    ['type': 'string',  'name': 'TLN_COMPONENT_PARAM_LSTN',   'flag': paramConstant.TLN_COMPONENT_PARAM_LSTN,     'default': '0.0.0.0' ],
    ['type': 'string',  'name': 'TLN_COMPONENT_PARAM_PORT',   'flag': paramConstant.TLN_COMPONENT_PARAM_PORT,     'default': '9080' ],
    ['type': 'string',  'name': 'TLN_COMPONENT_PARAM_PORTS',  'flag': paramConstant.TLN_COMPONENT_PARAM_PORTS,    'default': '9443' ],
    ['type': 'string',  'name': 'TLN_SHARED_COMPONENTS_HOME', 'flag': paramConstant.TLN_SHARED_COMPONENTS_HOME,   'default': '/tmp/tln' ],

    ['type': 'string',  'name': 'TLN_GITHUB_ACCESS_TOKEN',    'flag': paramConstant.TLN_GITHUB_ACCESS_TOKEN,      'default': '' ],

    ['type': 'string',  'name': 'TLN_SONARQUBE_SERVER',        'flag': paramConstant.SONARQUBE_SERVER,            'default': '' ],
    ['type': 'string',  'name': 'TLN_SONARQUBE_SCANNER',       'flag': paramConstant.SONARQUBE_SCANNER,           'default': '' ],
    ['type': 'boolean', 'name': 'TLN_SONARQUBE_QUALITY_GATES', 'flag': paramConstant.SONARQUBE_QUALITY_GATES,     'default': '' ],
    ['type': 'string',  'name': 'TLN_SONARQUBE_ACCESS_TOKEN',  'flag': paramConstant.SONARQUBE_ACCESS_TOKEN,      'default': '' ]
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

