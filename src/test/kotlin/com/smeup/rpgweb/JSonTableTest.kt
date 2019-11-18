package com.smeup.rpgweb

import com.google.gson.GsonBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

class JSonTableTest {
    @Test
    fun toJSon() {
        val lines = """
FIRSTNME
First name
LASTNAME
Last name
_##_ROWS
FIRSTNME_##_Dino
LASTNAME_##_Zoff
_##_ENDROW
FIRSTNME_##_Claudio
LASTNAME_##_Gentile
_##_ENDROW
FIRSTNME_##_Antonio
LASTNAME_##_Cabrini
_##_ENDROW
FIRSTNME_##_Gabriele
LASTNAME_##_Oriali
_##_ENDROW
FIRSTNME_##_Fulvio
LASTNAME_##_Collvati
_##_ENDROW
FIRSTNME_##_Gaetano
LASTNAME_##_Scirea
_##_ENDROW
FIRSTNME_##_Bruno
LASTNAME_##_Conti
_##_ENDROW
FIRSTNME_##_Marco
LASTNAME_##_Tardelli
_##_ENDROW
FIRSTNME_##_Paolo
LASTNAME_##_Rossi
_##_ENDROW
FIRSTNME_##_Giancarlo
LASTNAME_##_Antognoni
_##_ENDROW
FIRSTNME_##_Francesco
LASTNAME_##_Graziani
_##_ENDROW
        """.trimIndent()
        val expectedJson = """{
  "columns": [
    {
      "name": "FIRSTNME",
      "title": "First name"
    },
    {
      "name": "LASTNAME",
      "title": "Last name"
    }
  ],
  "rows": [
    {
      "cells": {
        "FIRSTNME": {
          "value": "Dino",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Zoff",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Claudio",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Gentile",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Antonio",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Cabrini",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Gabriele",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Oriali",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Fulvio",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Collvati",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Gaetano",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Scirea",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Bruno",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Conti",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Marco",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Tardelli",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Paolo",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Rossi",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Giancarlo",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Antognoni",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    },
    {
      "cells": {
        "FIRSTNME": {
          "value": "Francesco",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        },
        "LASTNAME": {
          "value": "Graziani",
          "obj": {
            "t": "",
            "p": "",
            "k": ""
          }
        }
      }
    }
  ]
}"""
        val table = JSonTable(lines.lines())
        val jsonString = GsonBuilder().setPrettyPrinting().create().toJson(table)
        println(jsonString)
        assertEquals(expectedJson, jsonString)
    }
}
