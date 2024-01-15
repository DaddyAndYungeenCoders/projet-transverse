package com.simulator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteResponseEntity {

    @JsonProperty("code")
    private String code;

    @JsonProperty("routes")
    private List<Route> routes;

    @JsonProperty("waypoints")
    private List<Waypoint> waypoints;

}

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class Route {
    @JsonProperty("legs")
    private List<Leg> legs;

    @JsonProperty("weight_name")
    private String weightName;

    @JsonProperty("weight")
    private double weight;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("distance")
    private double distance;

}

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class Leg {
    @JsonProperty("steps")
    private List<Step> steps;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("weight")
    private double weight;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("distance")
    private double distance;

}

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class Step {
    @JsonProperty("geometry")
    private String geometry;

    @JsonProperty("maneuver")
    private Maneuver maneuver;

    @JsonProperty("mode")
    private String mode;

    @JsonProperty("driving_side")
    private String drivingSide;

    @JsonProperty("name")
    private String name;

    @JsonProperty("intersections")
    private List<Intersection> intersections;

    @JsonProperty("weight")
    private double weight;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("distance")
    private double distance;

}

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class Maneuver {
    @JsonProperty("bearing_after")
    private int bearingAfter;

    @JsonProperty("bearing_before")
    private int bearingBefore;

    @JsonProperty("location")
    private List<Double> location;

    @JsonProperty("modifier")
    private String modifier;

    @JsonProperty("type")
    private String type;

}

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class Intersection {
    @JsonProperty("out")
    private int out;

    @JsonProperty("in")
    private int in;

    @JsonProperty("entry")
    private List<Boolean> entry;

    @JsonProperty("bearings")
    private List<Integer> bearings;

    @JsonProperty("location")
    private List<Double> location;


}

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class Waypoint {
    @JsonProperty("hint")
    private String hint;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private List<Double> location;
}

/*
{
    "code": "Ok",
    "routes": [
        {
            "legs": [
                {
                    "steps": [
                        {
                            "geometry": "siowGcgqd@LANKVc@",
                            "maneuver": {
                                "bearing_after": 172,
                                "bearing_before": 0,
                                "location": [
                                    6.15554,
                                    45.958821
                                ],
                                "modifier": "right",
                                "type": "depart"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "Route des Molliats",
                            "intersections": [
                                {
                                    "out": 0,
                                    "entry": [
                                        true
                                    ],
                                    "bearings": [
                                        172
                                    ],
                                    "location": [
                                        6.15554,
                                        45.958821
                                    ]
                                }
                            ],
                            "weight": 6.6,
                            "duration": 6.6,
                            "distance": 36.5
                        },
                        {
                            "geometry": "}gowGuhqd@bAdAHBD?FGpBgCHGHDPX",
                            "maneuver": {
                                "bearing_after": 215,
                                "bearing_before": 133,
                                "location": [
                                    6.155786,
                                    45.958554
                                ],
                                "modifier": "right",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "Rue du Champ de la Donne",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        60,
                                        210,
                                        315
                                    ],
                                    "location": [
                                        6.155786,
                                        45.958554
                                    ]
                                }
                            ],
                            "weight": 29.5,
                            "duration": 29.5,
                            "distance": 170.7
                        },
                        {
                            "geometry": "i`owGcjqd@HMDEFGPIRCL?pB`@PDR@pDDF?H?RDLFjDnCtBvB`@b@JJPFP?JEHIFMHYBYFY",
                            "maneuver": {
                                "bearing_after": 136,
                                "bearing_before": 225,
                                "location": [
                                    6.156015,
                                    45.957327
                                ],
                                "modifier": "left",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "ref": "D 173",
                            "driving_side": "right",
                            "name": "Route de la Vouettaz",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        135,
                                        285
                                    ],
                                    "location": [
                                        6.156015,
                                        45.957327
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        195,
                                        240
                                    ],
                                    "location": [
                                        6.154358,
                                        45.953355
                                    ]
                                }
                            ],
                            "weight": 50.900000000,
                            "duration": 50.900000000,
                            "distance": 558.6
                        },
                        {
                            "geometry": "wdnwG{bqd@NCbDd@vALhAERAPEVIVIPEP@NBPHNNp@l@TTh@f@PRJNZf@d@t@d@z@j@x@\\p@Tf@Zz@Ld@Lj@Jf@RbAJv@LdANzAFv@Ff@H\\JXJT^l@NT\\h@v@hAXl@hA|CDHXn@jDxG",
                            "maneuver": {
                                "bearing_after": 174,
                                "bearing_before": 108,
                                "location": [
                                    6.154862,
                                    45.952921
                                ],
                                "modifier": "right",
                                "type": "end of road"
                            },
                            "mode": "driving",
                            "ref": "D 173",
                            "driving_side": "right",
                            "name": "Route de Menthonnex",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        60,
                                        180,
                                        285
                                    ],
                                    "location": [
                                        6.154862,
                                        45.952921
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        105,
                                        210
                                    ],
                                    "location": [
                                        6.154432,
                                        45.950102
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        150,
                                        240
                                    ],
                                    "location": [
                                        6.152131,
                                        45.948485
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        255,
                                        330
                                    ],
                                    "location": [
                                        6.151177,
                                        45.948192
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        165,
                                        225
                                    ],
                                    "location": [
                                        6.149328,
                                        45.94779
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        150,
                                        225
                                    ],
                                    "location": [
                                        6.147283,
                                        45.946557
                                    ]
                                }
                            ],
                            "weight": 107.399999999,
                            "duration": 107.399999999,
                            "distance": 1195.7
                        },
                        {
                            "geometry": "svlwG{hod@Vp@Lb@b@|Bd@xB@VAR",
                            "maneuver": {
                                "bearing_after": 233,
                                "bearing_before": 227,
                                "location": [
                                    6.145579,
                                    45.945544
                                ],
                                "modifier": "straight",
                                "type": "new name"
                            },
                            "mode": "driving",
                            "ref": "D 173",
                            "driving_side": "right",
                            "name": "Route des Vignes",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        240,
                                        345
                                    ],
                                    "location": [
                                        6.145579,
                                        45.945544
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        120,
                                        240
                                    ],
                                    "location": [
                                        6.144521,
                                        45.945169
                                    ]
                                }
                            ],
                            "weight": 14.600000000,
                            "duration": 14.600000000,
                            "distance": 161.5
                        },
                        {
                            "geometry": "cslwGa}nd@BPDNBDDFDDTDn@HRBh@RNDh@h@v@t@JJRRNNBDFTBR@F",
                            "maneuver": {
                                "bearing_after": 246,
                                "bearing_before": 270,
                                "location": [
                                    6.143691,
                                    45.944975
                                ],
                                "modifier": "left",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "Route des Convers",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        135,
                                        240,
                                        315
                                    ],
                                    "location": [
                                        6.143691,
                                        45.944975
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        60,
                                        120,
                                        210,
                                        315
                                    ],
                                    "location": [
                                        6.14349,
                                        45.944913
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        45,
                                        195
                                    ],
                                    "location": [
                                        6.143393,
                                        45.944737
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        105,
                                        210
                                    ],
                                    "location": [
                                        6.142546,
                                        45.94346
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        120,
                                        225
                                    ],
                                    "location": [
                                        6.142467,
                                        45.94338
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        255
                                    ],
                                    "location": [
                                        6.14244,
                                        45.943356
                                    ]
                                }
                            ],
                            "weight": 33.900000000,
                            "duration": 33.900000000,
                            "distance": 233.8
                        },
                        {
                            "geometry": "qhlwGusnd@ADAH?F@D@BBDBBD@D?DABCBE@A@E",
                            "maneuver": {
                                "exit": 3,
                                "bearing_after": 295,
                                "bearing_before": 248,
                                "location": [
                                    6.142185,
                                    45.943291
                                ],
                                "modifier": "right",
                                "type": "roundabout"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "Route de Champ Moisi",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        135,
                                        300
                                    ],
                                    "location": [
                                        6.142185,
                                        45.943291
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        255,
                                        300
                                    ],
                                    "location": [
                                        6.142073,
                                        45.943305
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        15,
                                        180,
                                        330
                                    ],
                                    "location": [
                                        6.141955,
                                        45.943218
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        165,
                                        210
                                    ],
                                    "location": [
                                        6.141955,
                                        45.94319
                                    ]
                                },
                                {
                                    "out": 0,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        false,
                                        false
                                    ],
                                    "bearings": [
                                        120,
                                        255,
                                        300
                                    ],
                                    "location": [
                                        6.142034,
                                        45.943113
                                    ]
                                }
                            ],
                            "weight": 5.499999999,
                            "duration": 5.499999999,
                            "distance": 39.6
                        },
                        {
                            "geometry": "kglwG{rnd@JKLSB[Dm@Bi@BYFSDGDGBCFCLEXIXIVIJEpBk@V?z@UXIJCVSbAYjA]PGBANCZG`@ELDHF",
                            "maneuver": {
                                "exit": 3,
                                "bearing_after": 147,
                                "bearing_before": 115,
                                "location": [
                                    6.14206,
                                    45.943104
                                ],
                                "modifier": "slight right",
                                "type": "exit roundabout"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "Route de Champ Moisi",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        105,
                                        150,
                                        300
                                    ],
                                    "location": [
                                        6.14206,
                                        45.943104
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        75,
                                        165,
                                        345
                                    ],
                                    "location": [
                                        6.143693,
                                        45.941216
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        75,
                                        165,
                                        345
                                    ],
                                    "location": [
                                        6.14414,
                                        45.940194
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        150,
                                        195
                                    ],
                                    "location": [
                                        6.144276,
                                        45.939691
                                    ]
                                }
                            ],
                            "weight": 66.899999999,
                            "duration": 66.899999999,
                            "distance": 465.2
                        },
                        {
                            "geometry": "iqkwGi`od@BFDDDDF@DADA",
                            "maneuver": {
                                "exit": 2,
                                "bearing_after": 232,
                                "bearing_before": 199,
                                "location": [
                                    6.144209,
                                    45.939568
                                ],
                                "modifier": "slight right",
                                "type": "roundabout"
                            },
                            "mode": "driving",
                            "destinations": "Annecy",
                            "ref": "D 1203",
                            "driving_side": "right",
                            "name": "Avenue Marcel Dassault",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        15,
                                        60,
                                        240
                                    ],
                                    "location": [
                                        6.144209,
                                        45.939568
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        225,
                                        285
                                    ],
                                    "location": [
                                        6.144167,
                                        45.939546
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        0,
                                        165,
                                        300
                                    ],
                                    "location": [
                                        6.144105,
                                        45.939419
                                    ]
                                }
                            ],
                            "weight": 2.099999999,
                            "duration": 2.099999999,
                            "distance": 23.2
                        },
                        {
                            "geometry": "epkwGw_od@F@DBFDHFDFFJHLVd@HPJRf@jAHRHTHRHTFTHVLj@FTDTBTDTDPBVDZDXJdA^fDLlAFp@\\vCNzAFd@Fh@Np@Jb@L`@`@pAVbAlB|FxBpH",
                            "maneuver": {
                                "exit": 2,
                                "bearing_after": 196,
                                "bearing_before": 164,
                                "location": [
                                    6.144117,
                                    45.939387
                                ],
                                "modifier": "slight right",
                                "type": "exit roundabout"
                            },
                            "mode": "driving",
                            "destinations": "Annecy",
                            "ref": "D 1203",
                            "driving_side": "right",
                            "name": "Avenue Marcel Dassault",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        150,
                                        195,
                                        345
                                    ],
                                    "location": [
                                        6.144117,
                                        45.939387
                                    ]
                                },
                                {
                                    "lanes": [
                                        {
                                            "valid": true,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": false,
                                            "indications": [
                                                "slight right"
                                            ]
                                        }
                                    ],
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        255,
                                        270
                                    ],
                                    "location": [
                                        6.139471,
                                        45.937755
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        54,
                                        62,
                                        239
                                    ],
                                    "location": [
                                        6.136502,
                                        45.936946
                                    ]
                                }
                            ],
                            "weight": 59.3,
                            "duration": 59.3,
                            "distance": 916
                        },
                        {
                            "geometry": "uyjwGs~ld@b@fBJf@RbAD^Dn@@n@Cp@APIp@Mh@_@dAQ^mA`C_@x@ELKVM^EPENCPCLCRCPAPANAP?N?P?P@P?P@L@NBNBVBNBLBNDPDNHPLZLTV`@X^V^Vd@N^J\\L`@Fb@Hj@B\\@TDjDDhAD|@Dd@Fd@Jj@\\jBFh@Bl@?h@C^CZG\\I^KVKXUh@",
                            "maneuver": {
                                "bearing_after": 241,
                                "bearing_before": 239,
                                "location": [
                                    6.133704,
                                    45.935793
                                ],
                                "modifier": "slight right",
                                "type": "fork"
                            },
                            "mode": "driving",
                            "destinations": "A 41, Autres directions, Pringy, Z.I Pringy-Argonay",
                            "ref": "D 1203",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "lanes": [
                                        {
                                            "valid": false,
                                            "indications": [
                                                "slight left"
                                            ]
                                        },
                                        {
                                            "valid": true,
                                            "indications": [
                                                "slight right"
                                            ]
                                        }
                                    ],
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        238,
                                        243
                                    ],
                                    "location": [
                                        6.133704,
                                        45.935793
                                    ]
                                },
                                {
                                    "lanes": [
                                        {
                                            "valid": true,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": false,
                                            "indications": [
                                                "slight right"
                                            ]
                                        }
                                    ],
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        111,
                                        286,
                                        287
                                    ],
                                    "location": [
                                        6.129066,
                                        45.936593
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        75,
                                        240
                                    ],
                                    "location": [
                                        6.127514,
                                        45.9365
                                    ]
                                }
                            ],
                            "weight": 69.699999999,
                            "duration": 69.699999999,
                            "distance": 1107.2
                        },
                        {
                            "geometry": "ewjwGmnjd@}@zAq@vAsApCc@bA",
                            "maneuver": {
                                "bearing_after": 313,
                                "bearing_before": 307,
                                "location": [
                                    6.120869,
                                    45.935391
                                ],
                                "modifier": "slight right",
                                "type": "off ramp"
                            },
                            "mode": "driving",
                            "destinations": "A 41, Chambéry, Genève, Pringy, Cruseilles, St-Julien-en-Genevois, Centre Hospitalier",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        135,
                                        300,
                                        315
                                    ],
                                    "location": [
                                        6.120869,
                                        45.935391
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        128,
                                        134,
                                        309
                                    ],
                                    "location": [
                                        6.120408,
                                        45.935702
                                    ]
                                }
                            ],
                            "weight": 18,
                            "duration": 18,
                            "distance": 199.5
                        },
                        {
                            "geometry": "m~jwGcbjd@qAhCKHMB",
                            "maneuver": {
                                "bearing_after": 309,
                                "bearing_before": 306,
                                "location": [
                                    6.118903,
                                    45.936545
                                ],
                                "modifier": "slight right",
                                "type": "fork"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        120,
                                        300,
                                        315
                                    ],
                                    "location": [
                                        6.118903,
                                        45.936545
                                    ]
                                }
                            ],
                            "weight": 7.8,
                            "duration": 7.8,
                            "distance": 86.8
                        },
                        {
                            "geometry": "yakwGk}id@ICGAE?G@EBGFGHCJCJ?L?J",
                            "maneuver": {
                                "exit": 2,
                                "bearing_after": 18,
                                "bearing_before": 343,
                                "location": [
                                    6.118138,
                                    45.937092
                                ],
                                "modifier": "slight right",
                                "type": "roundabout"
                            },
                            "mode": "driving",
                            "destinations": "A 41: Lyon, Grenoble, Chambéry, Genève, Annemasse, Chamonix, Thonon-Évian",
                            "ref": "A41",
                            "driving_side": "right",
                            "name": "Annecy Nord, Sortie 17",
                            "intersections": [
                                {
                                    "out": 0,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        false
                                    ],
                                    "bearings": [
                                        15,
                                        165,
                                        210
                                    ],
                                    "location": [
                                        6.118138,
                                        45.937092
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        165,
                                        330
                                    ],
                                    "location": [
                                        6.118158,
                                        45.937249
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        120,
                                        285
                                    ],
                                    "location": [
                                        6.117986,
                                        45.937384
                                    ]
                                }
                            ],
                            "weight": 4.7,
                            "duration": 4.7,
                            "distance": 53.1
                        },
                        {
                            "geometry": "wckwGg{id@EVEPM^KVEHCDGDGFGBGBG@MAgBK",
                            "maneuver": {
                                "exit": 2,
                                "bearing_after": 286,
                                "bearing_before": 265,
                                "location": [
                                    6.117802,
                                    45.937399
                                ],
                                "modifier": "slight right",
                                "type": "exit roundabout"
                            },
                            "mode": "driving",
                            "destinations": "A 41: Lyon, Grenoble, Chambéry, Genève, Annemasse, Chamonix, Thonon-Évian",
                            "ref": "A41",
                            "driving_side": "right",
                            "name": "Annecy Nord, Sortie 17",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        255,
                                        285
                                    ],
                                    "location": [
                                        6.117802,
                                        45.937399
                                    ]
                                },
                                {
                                    "out": 0,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        150,
                                        180,
                                        285
                                    ],
                                    "location": [
                                        6.117122,
                                        45.937912
                                    ]
                                }
                            ],
                            "weight": 12.7,
                            "duration": 12.7,
                            "distance": 142.8
                        },
                        {
                            "geometry": "ejkwGkwid@OE",
                            "maneuver": {
                                "bearing_after": 16,
                                "bearing_before": 4,
                                "location": [
                                    6.117178,
                                    45.938434
                                ],
                                "modifier": "slight right",
                                "type": "fork"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 0,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        15,
                                        180,
                                        345
                                    ],
                                    "location": [
                                        6.117178,
                                        45.938434
                                    ]
                                }
                            ],
                            "weight": 0.7,
                            "duration": 0.7,
                            "distance": 9.1
                        },
                        {
                            "geometry": "ujkwGqwid@e@Ca@?i@?OF]Bm@Lw@NQFMFKHGFGHGFGLEHELCJCN",
                            "maneuver": {
                                "bearing_after": 2,
                                "bearing_before": 16,
                                "location": [
                                    6.117213,
                                    45.938512
                                ],
                                "modifier": "slight left",
                                "type": "fork"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 0,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        0,
                                        15,
                                        195
                                    ],
                                    "location": [
                                        6.117213,
                                        45.938512
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        150,
                                        180,
                                        345
                                    ],
                                    "location": [
                                        6.117234,
                                        45.939077
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        165,
                                        195,
                                        345
                                    ],
                                    "location": [
                                        6.117193,
                                        45.939162
                                    ]
                                }
                            ],
                            "weight": 19,
                            "duration": 19,
                            "distance": 220.2
                        },
                        {
                            "geometry": "{ukwGurid@APAN?P?P?J@J@TDRFXLb@\\`ADNl@rBPh@HZDN@LDT@R?N?LARCLANEJEJEHMLSLMFQDQ?MAKEOGKMIMGMEOEQCQ?QAOBUBWDUHOJSPOZSXMTIn@Wt@W`@Kh@ObCq@",
                            "maneuver": {
                                "bearing_after": 277,
                                "bearing_before": 293,
                                "location": [
                                    6.116434,
                                    45.940301
                                ],
                                "modifier": "slight left",
                                "type": "fork"
                            },
                            "mode": "driving",
                            "destinations": "A 41: Lyon, Grenoble, Chambéry",
                            "ref": "A41N",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        114,
                                        278,
                                        290
                                    ],
                                    "location": [
                                        6.116434,
                                        45.940301
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        120,
                                        300,
                                        330
                                    ],
                                    "location": [
                                        6.113167,
                                        45.939656
                                    ]
                                }
                            ],
                            "weight": 59.9,
                            "duration": 59.9,
                            "distance": 696
                        },
                        {
                            "geometry": "qikwGqjid@`AGfAEt@AtABz@BhAJt@HbANfARXHbBf@~Ah@bEdBtAj@vB|@dErBdCrApC`BzDnCfDlClCdCfCdC`HnHhKdLzCzCzCpCjC|BhCvBbXdTdEdD`BjAfBjA~@l@dAl@hAj@rAn@dBr@tBt@`FxAnA`@l@Ph@LlAZvAb@nA\\bJjChCx@bA^v@Z|@`@~@f@tAx@x@h@rA~@~CbC|D`D|BhBhEfDnAz@`Al@|BrAzDvBjFrBzB|@xDrA~FlBzBr@tBr@fDfA~F|BxAl@zBbAbBz@bEzBvBrAzBzAbEzCtC~BfF|ExA|ApD`EpGdIV\\rLnOtMxPhWp\\dOtRhBzBnB|B|AfB~AbB`A`AdA`AbAz@`Ax@|AjA`BhAz@h@|@h@bAj@|@d@pB~@tBz@xAh@zAd@x@Tv@RXHlAVzAZ|ATbBRdBP|AJbBFdBDhB@hD?~EGvRa@jEIlFKzFKbCGlAC`BCvBCjDAhCDdADhAFpAJn@Ft@JxATrAVrBf@fAZr@Tl@TtAh@t@\\x@^`Ah@jAp@p@`@v@f@dBpAz@r@x@r@nBlBx@|@x@~@t@`Ar@|@`AvAbA|AfAjBfAjBvAnC|A~C~S~b@tAvClAnCnAzCjAxCdAtC~@pCbA~Cx@rCz@zChDlMpDjN|AhG~@pDbAtDpB|HdA|Dl@~BjIt[|CxLrAjFpAhFtBxIVfAzAzG",
                            "maneuver": {
                                "bearing_after": 174,
                                "bearing_before": 164,
                                "location": [
                                    6.115125,
                                    45.938325
                                ],
                                "modifier": "slight left",
                                "type": "merge"
                            },
                            "mode": "driving",
                            "ref": "A 41",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 0,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        false
                                    ],
                                    "bearings": [
                                        175,
                                        346,
                                        351
                                    ],
                                    "location": [
                                        6.115125,
                                        45.938325
                                    ]
                                },
                                {
                                    "lanes": [
                                        {
                                            "valid": true,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": true,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": true,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": false,
                                            "indications": [
                                                "slight right"
                                            ]
                                        }
                                    ],
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        195,
                                        210
                                    ],
                                    "location": [
                                        6.088201,
                                        45.897917
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        11,
                                        18,
                                        197
                                    ],
                                    "location": [
                                        6.086886,
                                        45.895185
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        40,
                                        220,
                                        223
                                    ],
                                    "location": [
                                        6.074066,
                                        45.878908
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        39,
                                        40,
                                        220
                                    ],
                                    "location": [
                                        6.066491,
                                        45.872674
                                    ]
                                },
                                {
                                    "lanes": [
                                        {
                                            "valid": true,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": true,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": false,
                                            "indications": [
                                                "slight right"
                                            ]
                                        }
                                    ],
                                    "out": 0,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        178,
                                        186,
                                        358
                                    ],
                                    "location": [
                                        6.056518,
                                        45.851207
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        0,
                                        180,
                                        345
                                    ],
                                    "location": [
                                        6.056642,
                                        45.848762
                                    ]
                                }
                            ],
                            "weight": 610.9,
                            "duration": 610.9,
                            "distance": 16390.4
                        },
                        {
                            "geometry": "o~svGoduc@VbCBR@L@L?P?P?NAPCNCPGXIZ]hAIVCLCLCJAPAJ?L@JB\\@PBJDHBHDHBDFHFFHDFDHBH@L@J@J?TAd@E`@C|AOd@E`@CNCR?J?H?H@HBF@FB^^LLDJHLJXFNDLJn@Lz@b@hB^pAl@zBNx@Ff@PvAL|@BP",
                            "maneuver": {
                                "bearing_after": 254,
                                "bearing_before": 244,
                                "location": [
                                    6.011758,
                                    45.8188
                                ],
                                "modifier": "slight right",
                                "type": "off ramp"
                            },
                            "mode": "driving",
                            "destinations": "Rumilly, Alby ˢ\/ Chéran",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "lanes": [
                                        {
                                            "valid": false,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": false,
                                            "indications": [
                                                "straight"
                                            ]
                                        },
                                        {
                                            "valid": true,
                                            "indications": [
                                                "slight right"
                                            ]
                                        }
                                    ],
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        240,
                                        255
                                    ],
                                    "location": [
                                        6.011758,
                                        45.8188
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        90,
                                        225
                                    ],
                                    "location": [
                                        6.008951,
                                        45.819034
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        15,
                                        195,
                                        210
                                    ],
                                    "location": [
                                        6.008569,
                                        45.816668
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        75,
                                        240
                                    ],
                                    "location": [
                                        6.007392,
                                        45.816102
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        150,
                                        240,
                                        270
                                    ],
                                    "location": [
                                        6.006446,
                                        45.815764
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        75,
                                        255
                                    ],
                                    "location": [
                                        6.005344,
                                        45.81541
                                    ]
                                }
                            ],
                            "weight": 74.2,
                            "duration": 74.2,
                            "distance": 875.2
                        },
                        {
                            "geometry": "ehsvGcwsc@ERCR@TDR",
                            "maneuver": {
                                "exit": 1,
                                "bearing_after": 295,
                                "bearing_before": 251,
                                "location": [
                                    6.004495,
                                    45.815227
                                ],
                                "modifier": "right",
                                "type": "roundabout"
                            },
                            "mode": "driving",
                            "ref": "D 3",
                            "driving_side": "right",
                            "name": "Route de Rumilly",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        135,
                                        300
                                    ],
                                    "location": [
                                        6.004495,
                                        45.815227
                                    ]
                                }
                            ],
                            "weight": 4.3,
                            "duration": 4.3,
                            "distance": 33.6
                        },
                        {
                            "geometry": "ghsvGqtsc@w@t@c@XcCxAoDxBq@Xu@^c@XmBlA",
                            "maneuver": {
                                "exit": 1,
                                "bearing_after": 324,
                                "bearing_before": 246,
                                "location": [
                                    6.004089,
                                    45.815238
                                ],
                                "modifier": "right",
                                "type": "exit roundabout"
                            },
                            "mode": "driving",
                            "ref": "D 3",
                            "driving_side": "right",
                            "name": "Route de Rumilly",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        225,
                                        330
                                    ],
                                    "location": [
                                        6.004089,
                                        45.815238
                                    ]
                                },
                                {
                                    "out": 3,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        150,
                                        240,
                                        330
                                    ],
                                    "location": [
                                        6.003237,
                                        45.816357
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        150,
                                        330
                                    ],
                                    "location": [
                                        6.002631,
                                        45.817236
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        150,
                                        240,
                                        330
                                    ],
                                    "location": [
                                        6.002336,
                                        45.817758
                                    ]
                                }
                            ],
                            "weight": 37.400000000,
                            "duration": 37.400000000,
                            "distance": 402.4
                        },
                        {
                            "geometry": "q|svGkfsc@AX?D?D?F@D@DDL`@h@FJDHDFDJDHDJfAhD",
                            "maneuver": {
                                "bearing_after": 275,
                                "bearing_before": 331,
                                "location": [
                                    6.001819,
                                    45.818486
                                ],
                                "modifier": "left",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "destinations": "D 1201: Chambéry, Saint-Félix",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "lanes": [
                                        {
                                            "valid": true,
                                            "indications": [
                                                "left"
                                            ]
                                        },
                                        {
                                            "valid": false,
                                            "indications": [
                                                "straight"
                                            ]
                                        }
                                    ],
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        150,
                                        270,
                                        330
                                    ],
                                    "location": [
                                        6.001819,
                                        45.818486
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        90,
                                        150,
                                        240,
                                        330
                                    ],
                                    "location": [
                                        6.001694,
                                        45.818495
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        15,
                                        75,
                                        225
                                    ],
                                    "location": [
                                        6.001457,
                                        45.818446
                                    ]
                                }
                            ],
                            "weight": 15.1,
                            "duration": 15.1,
                            "distance": 164.2
                        },
                        {
                            "geometry": "ywsvGo{rc@zChOdAnF",
                            "maneuver": {
                                "bearing_after": 246,
                                "bearing_before": 237,
                                "location": [
                                    6.000077,
                                    45.817729
                                ],
                                "modifier": "straight",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "ref": "D 1201",
                            "driving_side": "right",
                            "name": "Route d'Aix-les-Bains",
                            "intersections": [
                                {
                                    "out": 3,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        59,
                                        68,
                                        80,
                                        247
                                    ],
                                    "location": [
                                        6.000077,
                                        45.817729
                                    ]
                                }
                            ],
                            "weight": 18.1,
                            "duration": 18.1,
                            "distance": 321.3
                        },
                        {
                            "geometry": "wpsvGucrc@jBvJlAjG|EtVn@tBt@lBVf@hBfD|@`BVf@JTpClEvAfApLtGvEhCb@TxFfD^Vb@`@j@p@\\b@dDbFn@fA~ExIpGdLZj@LPb@t@NTfAtBLPjD~FV`@JR",
                            "maneuver": {
                                "bearing_after": 247,
                                "bearing_before": 246,
                                "location": [
                                    5.996272,
                                    45.816597
                                ],
                                "modifier": "straight",
                                "type": "new name"
                            },
                            "mode": "driving",
                            "ref": "D 1201",
                            "driving_side": "right",
                            "name": "Route d'Annecy",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        255
                                    ],
                                    "location": [
                                        5.996272,
                                        45.816597
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        135,
                                        240
                                    ],
                                    "location": [
                                        5.994386,
                                        45.81606
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        120,
                                        225
                                    ],
                                    "location": [
                                        5.987081,
                                        45.813395
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        315
                                    ],
                                    "location": [
                                        5.986585,
                                        45.81309
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        180,
                                        225
                                    ],
                                    "location": [
                                        5.986391,
                                        45.812971
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        135,
                                        210,
                                        315
                                    ],
                                    "location": [
                                        5.983502,
                                        45.809569
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        315
                                    ],
                                    "location": [
                                        5.977907,
                                        45.804157
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        135,
                                        225
                                    ],
                                    "location": [
                                        5.975797,
                                        45.802793
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        300
                                    ],
                                    "location": [
                                        5.975489,
                                        45.802584
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        270
                                    ],
                                    "location": [
                                        5.97315,
                                        45.801029
                                    ]
                                }
                            ],
                            "weight": 174.2,
                            "duration": 174.2,
                            "distance": 2611.6
                        },
                        {
                            "geometry": "inpvGoqmc@^p@PZbCfEJTjApB`AfBNPJPb@n@p@|@P^FZHVFNRZFH",
                            "maneuver": {
                                "bearing_after": 226,
                                "bearing_before": 226,
                                "location": [
                                    5.972878,
                                    45.800848
                                ],
                                "modifier": "straight",
                                "type": "new name"
                            },
                            "mode": "driving",
                            "ref": "D 1201",
                            "driving_side": "right",
                            "name": "Route d'Aix-Les-Bains",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        345
                                    ],
                                    "location": [
                                        5.972878,
                                        45.800848
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        135,
                                        225
                                    ],
                                    "location": [
                                        5.972633,
                                        45.800689
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        150,
                                        225
                                    ],
                                    "location": [
                                        5.971377,
                                        45.799875
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        345
                                    ],
                                    "location": [
                                        5.970806,
                                        45.799504
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        285
                                    ],
                                    "location": [
                                        5.970289,
                                        45.799168
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        135,
                                        225
                                    ],
                                    "location": [
                                        5.969873,
                                        45.798854
                                    ]
                                }
                            ],
                            "weight": 38.5,
                            "duration": 38.5,
                            "distance": 427
                        },
                        {
                            "geometry": "_~ovGmxlc@?FDFF@",
                            "maneuver": {
                                "exit": 1,
                                "bearing_after": 257,
                                "bearing_before": 217,
                                "location": [
                                    5.968869,
                                    45.79824
                                ],
                                "modifier": "slight right",
                                "type": "roundabout"
                            },
                            "mode": "driving",
                            "ref": "D 1201",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        105,
                                        255
                                    ],
                                    "location": [
                                        5.968869,
                                        45.79824
                                    ]
                                }
                            ],
                            "weight": 1.2,
                            "duration": 1.2,
                            "distance": 12.1
                        },
                        {
                            "geometry": "q}ovG{wlc@HPNRh@^nDnF`CpDPRb@h@tCrEzDhG|BlDHLXb@~DtFfF|HnDnFnExGHJNVBDz@pAV^T^P`@LZN\\Lb@H`@H`@Nr@ZhBVvA",
                            "maneuver": {
                                "exit": 1,
                                "bearing_after": 232,
                                "bearing_before": 187,
                                "location": [
                                    5.968783,
                                    45.798166
                                ],
                                "modifier": "right",
                                "type": "exit roundabout"
                            },
                            "mode": "driving",
                            "ref": "D 1201",
                            "driving_side": "right",
                            "name": "",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        150,
                                        240
                                    ],
                                    "location": [
                                        5.968783,
                                        45.798166
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        120,
                                        225
                                    ],
                                    "location": [
                                        5.96723,
                                        45.796952
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        120,
                                        225,
                                        285
                                    ],
                                    "location": [
                                        5.966239,
                                        45.796214
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225,
                                        300
                                    ],
                                    "location": [
                                        5.962771,
                                        45.793713
                                    ]
                                }
                            ],
                            "weight": 110.699999999,
                            "duration": 110.699999999,
                            "distance": 1624.1
                        },
                        {
                            "geometry": "k~mvGc{ic@EDCFAHAH@F@FBFBDDBD@DAFAlB|L~@`FnAlHHb@DR@FP|@f@xCH`@TlAF`@l@|CXjA",
                            "maneuver": {
                                "exit": 1,
                                "bearing_after": 316,
                                "bearing_before": 247,
                                "location": [
                                    5.953939,
                                    45.788064
                                ],
                                "modifier": "straight",
                                "type": "roundabout turn"
                            },
                            "mode": "driving",
                            "ref": "D 1201",
                            "driving_side": "right",
                            "name": "Rue du Mont-Blanc",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        165,
                                        315
                                    ],
                                    "location": [
                                        5.953939,
                                        45.788064
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        150,
                                        255,
                                        345
                                    ],
                                    "location": [
                                        5.953611,
                                        45.787941
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        180,
                                        255
                                    ],
                                    "location": [
                                        5.948742,
                                        45.78667
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        75,
                                        255
                                    ],
                                    "location": [
                                        5.948564,
                                        45.786619
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        180,
                                        255
                                    ],
                                    "location": [
                                        5.94811,
                                        45.786494
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        165,
                                        255
                                    ],
                                    "location": [
                                        5.947168,
                                        45.786235
                                    ]
                                }
                            ],
                            "weight": 67.699999999,
                            "duration": 67.699999999,
                            "distance": 725.8
                        },
                        {
                            "geometry": "yomvG_fhc@ARANKfAIx@HzB^rE@P`@lDD^BJj@nCPz@Lp@Hv@Fl@?P?N?NAx@?`@Dh@@NBNBDDL~@dAz@xAlAvC~@hAlCnCzBzBjAxAFRVt@Zz@d@n@nAx@TTf@h@vBhDVb@dArA^b@bAlAn@dA~AbE`@nBv@rCHXvAfEvA|DnA`Cl@~AXb@j@^v@hANVBDFFr@l@d@zB",
                            "maneuver": {
                                "bearing_after": 282,
                                "bearing_before": 243,
                                "location": [
                                    5.945435,
                                    45.785726
                                ],
                                "modifier": "slight right",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "ref": "D 991c",
                            "driving_side": "right",
                            "name": "Rue de la Chambotte",
                            "intersections": [
                                {
                                    "out": 4,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        15,
                                        60,
                                        120,
                                        195,
                                        285
                                    ],
                                    "location": [
                                        5.945435,
                                        45.785726
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        105,
                                        270
                                    ],
                                    "location": [
                                        5.944607,
                                        45.785864
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        90,
                                        135,
                                        255,
                                        315
                                    ],
                                    "location": [
                                        5.943992,
                                        45.785811
                                    ]
                                },
                                {
                                    "out": 3,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        60,
                                        165,
                                        255
                                    ],
                                    "location": [
                                        5.940734,
                                        45.785106
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        240,
                                        285
                                    ],
                                    "location": [
                                        5.938891,
                                        45.784897
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        210,
                                        240
                                    ],
                                    "location": [
                                        5.93497,
                                        45.781769
                                    ]
                                }
                            ],
                            "weight": 143,
                            "duration": 143,
                            "distance": 2148.6
                        },
                        {
                            "geometry": "imkvGs_dc@D\\Fx@x@tAd@hCJdAEnCFjDFvBOh@IxA\\bFFzABb@VhCt@vB|DtFrAzBt@`@PNNVLXX~@r@xAb@Td@?l@h@XfAPvAr@fAz@f@lAVr@F\\HRN",
                            "maneuver": {
                                "bearing_after": 254,
                                "bearing_before": 244,
                                "location": [
                                    5.923943,
                                    45.775087
                                ],
                                "modifier": "straight",
                                "type": "new name"
                            },
                            "mode": "driving",
                            "ref": "D 991c",
                            "driving_side": "right",
                            "name": "Route des Combes",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        255
                                    ],
                                    "location": [
                                        5.923943,
                                        45.775087
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        90,
                                        255
                                    ],
                                    "location": [
                                        5.917593,
                                        45.774369
                                    ]
                                }
                            ],
                            "weight": 87,
                            "duration": 87,
                            "distance": 1317.5
                        },
                        {
                            "geometry": "yijvG}nac@Ar@CJCH",
                            "maneuver": {
                                "bearing_after": 274,
                                "bearing_before": 206,
                                "location": [
                                    5.911032,
                                    45.769409
                                ],
                                "modifier": "right",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "ref": "D 991b",
                            "driving_side": "right",
                            "name": "Route de la Chambotte",
                            "intersections": [
                                {
                                    "out": 3,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        75,
                                        165,
                                        270
                                    ],
                                    "location": [
                                        5.911032,
                                        45.769409
                                    ]
                                }
                            ],
                            "weight": 2.6,
                            "duration": 2.6,
                            "distance": 30
                        },
                        {
                            "geometry": "cjjvGslac@m@lAKPw@`Be@fAe@hAWj@eAjC_@x@e@fAe@zA}AdFQb@W`@m@t@]\\GLCNC`@Af@?f@Hx@Jt@B`@Bf@?r@?D@l@BRDPNRPT^f@PXBJ@PAJAHIXeA`Cc@`Ac@`AI\\G^QzAM~AGx@Cx@A`AA~@CbC?\\?\\B\\@\\@XAXEPGROf@_@jAM`@Qb@OXUZk@x@g@t@g@z@Ud@KPKNSNYJc@Pe@RYR[RYTWXKJq@t@UPYPm@VA?m@Z_@Va@Rw@P",
                            "maneuver": {
                                "bearing_after": 309,
                                "bearing_before": 275,
                                "location": [
                                    5.910659,
                                    45.769458
                                ],
                                "modifier": "slight right",
                                "type": "fork"
                            },
                            "mode": "driving",
                            "ref": "D 991b",
                            "driving_side": "right",
                            "name": "Route de la Chambotte",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        255,
                                        315
                                    ],
                                    "location": [
                                        5.910659,
                                        45.769458
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        135,
                                        315
                                    ],
                                    "location": [
                                        5.910177,
                                        45.769747
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        120,
                                        315
                                    ],
                                    "location": [
                                        5.908044,
                                        45.770882
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        135,
                                        270
                                    ],
                                    "location": [
                                        5.902048,
                                        45.77192
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        150,
                                        330
                                    ],
                                    "location": [
                                        5.892263,
                                        45.776655
                                    ]
                                }
                            ],
                            "weight": 139.600000000,
                            "duration": 139.600000000,
                            "distance": 1891.6
                        },
                        {
                            "geometry": "}zkvGuw}b@?N?JEFgA~BINKNUNSJOHMLMXI^Kf@Gl@AbB@T",
                            "maneuver": {
                                "bearing_after": 268,
                                "bearing_before": 345,
                                "location": [
                                    5.891948,
                                    45.777273
                                ],
                                "modifier": "left",
                                "type": "end of road"
                            },
                            "mode": "driving",
                            "ref": "D 991b",
                            "driving_side": "right",
                            "name": "Route de la Chambotte",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        165,
                                        270
                                    ],
                                    "location": [
                                        5.891948,
                                        45.777273
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        105,
                                        270
                                    ],
                                    "location": [
                                        5.889988,
                                        45.778352
                                    ]
                                }
                            ],
                            "weight": 22.5,
                            "duration": 22.5,
                            "distance": 250.3
                        },
                        {
                            "geometry": "ualvGsg}b@EJAf@?DEdADf@Nf@BN@V@J",
                            "maneuver": {
                                "bearing_after": 275,
                                "bearing_before": 268,
                                "location": [
                                    5.889384,
                                    45.778348
                                ],
                                "modifier": "straight",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "driving_side": "right",
                            "name": "Chemin de Lassy",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        240,
                                        270
                                    ],
                                    "location": [
                                        5.889384,
                                        45.778348
                                    ]
                                }
                            ],
                            "weight": 15.4,
                            "duration": 15.4,
                            "distance": 106
                        },
                        {
                            "geometry": "ealvGo_}b@Sd@CFOTW\\QPWLWNs@n@s@r@i@ZQPMTMZE\\B^D`@DTATELGJSVQRCH?F@F@FB@BBB?F?HE\\]NGPAZA\\BPLPVL\\L`@ZjA\\fAFRFT@N@NKfB?XZvBP~BNdBJdBFxABp@?p@G|@Kx@Mp@Op@Wp@[l@GJKJIJMH]TOJKJOXKXCd@Cd@ChCAXCXI\\Kj@Er@Aj@Bh@H`ADVBJDFDFFBP?RCtCeBzAs@`@YPQHGHENCP?FDFHJRLRNFLJDLDTBh@Dj@Hn@Dt@G`AE`A?^@\\B\\FZFTJNJDPBtA?l@An@BJLVL`@L\\BL@FB@F@FAFCBE@IAcAWkAYU@SBOFYHK@OAOCg@KU@SDYNURQ^UZYR_@J_APwAx@kAn@kAl@iAn@UHYP_Bj@cEdAm@^i@d@m@b@WRULk@\\g@\\oA`Aa@d@_@d@c@p@e@t@a@r@_@p@_@d@c@j@EF[^MJOJ_@HS@S?OBMDi@T{@b@u@n@MPIRINOHK@K@IBKLIJMHUFSBSBIF}@n@a@Ha@JUNOPQVQZo@v@w@v@URo@`@k@Z[Ns@LuAh@{@XYJiAVQ?QEg@WMAM@UNGFKDOAMEQCM?g@XUJUFuCD[@[Do@JQBS@o@Ck@KS@UBe@FKAOAMCUGSOe@?_CCm@Gk@Ma@CaACa@Jw@F]J}APgAL_@L[Nq@^OJONSPSJu@LgAd@_Ab@MJKLUZSXULWJUHWJs@h@c@\\e@X_@Nc@L{@P_ANQBSFy@h@{@j@wBnCgAv@m@\\m@T]H[NWN[L[F[HMHEDORGDIHSJ_AVgAT}A`AuCpA}BzAm@`@]LWLSNQRkAdBc@Xg@Z[RWVoBdD[j@Ob@Cv@Cf@CNGPCFuBbEKVEP?N@LDJTr@JVHRBBFFHBH?LAf@]FAF@JPBHBJ",
                            "maneuver": {
                                "bearing_after": 305,
                                "bearing_before": 258,
                                "location": [
                                    5.88808,
                                    45.778271
                                ],
                                "modifier": "right",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "ref": "D 991b",
                            "driving_side": "right",
                            "name": "Route de la Chambotte",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        120,
                                        300
                                    ],
                                    "location": [
                                        5.88808,
                                        45.778271
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        135,
                                        180,
                                        330,
                                        345
                                    ],
                                    "location": [
                                        5.887588,
                                        45.778587
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        180,
                                        210
                                    ],
                                    "location": [
                                        5.875662,
                                        45.777514
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        150,
                                        255
                                    ],
                                    "location": [
                                        5.874667,
                                        45.777038
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        150,
                                        195,
                                        345
                                    ],
                                    "location": [
                                        5.85699,
                                        45.806985
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        135,
                                        210,
                                        315
                                    ],
                                    "location": [
                                        5.853796,
                                        45.81064
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        true,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        135,
                                        270
                                    ],
                                    "location": [
                                        5.853404,
                                        45.810863
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        120,
                                        270,
                                        345
                                    ],
                                    "location": [
                                        5.851521,
                                        45.811657
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        180,
                                        240
                                    ],
                                    "location": [
                                        5.850912,
                                        45.81089
                                    ]
                                }
                            ],
                            "weight": 623.899999999,
                            "duration": 623.899999999,
                            "distance": 6755.7
                        },
                        {
                            "geometry": "mlrvG}uub@?F@LHPJDL?JI",
                            "maneuver": {
                                "exit": 2,
                                "bearing_after": 260,
                                "bearing_before": 236,
                                "location": [
                                    5.850707,
                                    45.810793
                                ],
                                "modifier": "slight right",
                                "type": "roundabout"
                            },
                            "mode": "driving",
                            "ref": "D 914",
                            "driving_side": "right",
                            "name": "Route de Châtillon",
                            "intersections": [
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        60,
                                        105,
                                        255
                                    ],
                                    "location": [
                                        5.850707,
                                        45.810793
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        75,
                                        255,
                                        315
                                    ],
                                    "location": [
                                        5.850671,
                                        45.810789
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 1,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        45,
                                        210
                                    ],
                                    "location": [
                                        5.850514,
                                        45.810731
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        180,
                                        285
                                    ],
                                    "location": [
                                        5.850475,
                                        45.810666
                                    ]
                                }
                            ],
                            "weight": 2.400000000,
                            "duration": 2.400000000,
                            "distance": 40.5
                        },
                        {
                            "geometry": "{jrvGytub@PCLAJB@@HFHD~AxAd@Zl@ZDB`@Pn@Zb@TTTj@p@~AfBnBjC",
                            "maneuver": {
                                "exit": 2,
                                "bearing_after": 177,
                                "bearing_before": 147,
                                "location": [
                                    5.850531,
                                    45.810535
                                ],
                                "modifier": "slight right",
                                "type": "exit roundabout"
                            },
                            "mode": "driving",
                            "ref": "D 914",
                            "driving_side": "right",
                            "name": "Route de Châtillon",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        120,
                                        180,
                                        330
                                    ],
                                    "location": [
                                        5.850531,
                                        45.810535
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        0,
                                        30,
                                        210
                                    ],
                                    "location": [
                                        5.850542,
                                        45.810323
                                    ]
                                }
                            ],
                            "weight": 27.8,
                            "duration": 27.8,
                            "distance": 423.3
                        },
                        {
                            "geometry": "wvqvGucub@xApBJRBLBN?PC\\Cl@AT?V@V@TD\\FXNr@t@bCF\\DX@N?P?ZCXANu@nEm@xDo@vD_@jBaB~GqA|FmAnFcDhNI\\IZIPKRINKLQTe@d@uBvB[\\EHCFAJAJF|A?RATGrACb@@RBLDNDJNTFHFDHDHB~@HN?NBZNRPfAfA`A~@f@|@\\tALtAFxBLhBZrApAdE|AjE|AvC`@|@Ln@d@zETjCZhDFdA@fC@n@D^J^JTHTLRJNLP`AlAZ\\Z\\d@r@NP\\f@\\JVFZBRBp@@`A@v@?l@Hv@LjDf@F@@?vANxAEfBQfCo@dJkArCV`CUfCWVC~ASd@KPGNKfBmBRQXUvAcAbAo@NOTQX]X_@VWNIJEXM|B}@jEaBfBq@NGXKPGh@[n@]VSLSLWVa@Va@TUFEHE^IXEXEPCPERKLMJOHMNYHIHE",
                            "maneuver": {
                                "bearing_after": 220,
                                "bearing_before": 220,
                                "location": [
                                    5.847794,
                                    45.807317
                                ],
                                "modifier": "straight",
                                "type": "new name"
                            },
                            "mode": "driving",
                            "ref": "D 914",
                            "driving_side": "right",
                            "name": "Route de Portout",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        225
                                    ],
                                    "location": [
                                        5.847794,
                                        45.807317
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        180,
                                        255
                                    ],
                                    "location": [
                                        5.847051,
                                        45.806785
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        105,
                                        225
                                    ],
                                    "location": [
                                        5.81788,
                                        45.805045
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        45,
                                        135,
                                        225,
                                        300
                                    ],
                                    "location": [
                                        5.817785,
                                        45.804973
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        45,
                                        195,
                                        345
                                    ],
                                    "location": [
                                        5.81759,
                                        45.804821
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        45,
                                        150,
                                        330
                                    ],
                                    "location": [
                                        5.819563,
                                        45.793507
                                    ]
                                },
                                {
                                    "out": 0,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        165,
                                        255,
                                        345
                                    ],
                                    "location": [
                                        5.820544,
                                        45.792121
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        135,
                                        150,
                                        330
                                    ],
                                    "location": [
                                        5.821558,
                                        45.790074
                                    ]
                                },
                                {
                                    "out": 0,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        150,
                                        285,
                                        330
                                    ],
                                    "location": [
                                        5.822475,
                                        45.789222
                                    ]
                                }
                            ],
                            "weight": 314.299999999,
                            "duration": 314.299999999,
                            "distance": 4783.1
                        },
                        {
                            "geometry": "m~mvG}ipb@FDDD@LAJGXI^c@hC?j@ARCFO\\KPMXOd@ERITEP?HBH",
                            "maneuver": {
                                "bearing_after": 213,
                                "bearing_before": 150,
                                "location": [
                                    5.823187,
                                    45.788072
                                ],
                                "modifier": "right",
                                "type": "continue"
                            },
                            "mode": "driving",
                            "ref": "D 914",
                            "driving_side": "right",
                            "name": "Route du Bord du Lac",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 2,
                                    "entry": [
                                        true,
                                        true,
                                        false
                                    ],
                                    "bearings": [
                                        120,
                                        210,
                                        330
                                    ],
                                    "location": [
                                        5.823187,
                                        45.788072
                                    ]
                                },
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        105,
                                        270,
                                        345
                                    ],
                                    "location": [
                                        5.822016,
                                        45.788268
                                    ]
                                },
                                {
                                    "out": 2,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        180,
                                        315
                                    ],
                                    "location": [
                                        5.821658,
                                        45.788295
                                    ]
                                }
                            ],
                            "weight": 20,
                            "duration": 20,
                            "distance": 219
                        },
                        {
                            "geometry": "gbnvGkzob@BBD?FAHE`@WLGLCRAZ?`@ATCVG~@YHEDIBMBO?Q@YDg@VuALa@HOFQJUFUBUD[DODON[T_@JKLIn@a@LGt@]ZMPCD?RAjDQRENK|@s@PKRKD?FBBF",
                            "maneuver": {
                                "bearing_after": 170,
                                "bearing_before": 262,
                                "location": [
                                    5.820702,
                                    45.788683
                                ],
                                "modifier": "left",
                                "type": "end of road"
                            },
                            "mode": "driving",
                            "ref": "D 914",
                            "driving_side": "right",
                            "name": "Route du Bord du Lac",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        90,
                                        165,
                                        345
                                    ],
                                    "location": [
                                        5.820702,
                                        45.788683
                                    ]
                                }
                            ],
                            "weight": 46.6,
                            "duration": 46.6,
                            "distance": 695
                        },
                        {
                            "geometry": "icmvGirpb@pE_C|DeBnAo@pBmAp@KtAAlCe@~Bs@hAm@vBk@x@StCoA`@WRSRM~Aq@b@[n@_@jBi@",
                            "maneuver": {
                                "bearing_after": 156,
                                "bearing_before": 210,
                                "location": [
                                    5.824529,
                                    45.783726
                                ],
                                "modifier": "left",
                                "type": "turn"
                            },
                            "mode": "driving",
                            "ref": "D 18",
                            "driving_side": "right",
                            "name": "Route de l’Abbaye",
                            "intersections": [
                                {
                                    "out": 1,
                                    "in": 0,
                                    "entry": [
                                        false,
                                        true,
                                        true
                                    ],
                                    "bearings": [
                                        30,
                                        150,
                                        285
                                    ],
                                    "location": [
                                        5.824529,
                                        45.783726
                                    ]
                                }
                            ],
                            "weight": 93.8,
                            "duration": 93.8,
                            "distance": 1041.8
                        },
                        {
                            "geometry": "elkvGemqb@",
                            "maneuver": {
                                "bearing_after": 0,
                                "bearing_before": 165,
                                "location": [
                                    5.828826,
                                    45.774912
                                ],
                                "type": "arrive"
                            },
                            "mode": "driving",
                            "ref": "D 18",
                            "driving_side": "right",
                            "name": "Route de l’Abbaye",
                            "intersections": [
                                {
                                    "in": 0,
                                    "entry": [
                                        true
                                    ],
                                    "bearings": [
                                        345
                                    ],
                                    "location": [
                                        5.828826,
                                        45.774912
                                    ]
                                }
                            ],
                            "weight": 0,
                            "duration": 0,
                            "distance": 0
                        }
                    ],
                    "summary": "A 41, Route de la Chambotte",
                    "weight": 3260.4,
                    "duration": 3260.4,
                    "distance": 49605.9
                }
            ],
            "weight_name": "routability",
            "weight": 3260.4,
            "duration": 3260.4,
            "distance": 49605.9
        }
    ],
    "waypoints": [
        {
            "hint": "w7cjgf_1docLAAAAAAAAACoAAADqAAAAv9LwQAAAAAAazudBPK0iQwsAAAAAAAAAKgAAAOoAAAC6AgEAFO1dAKVGvQKY4F0AhEa9AgIA_xW0OfpJ",
            "distance": 247.711337889,
            "name": "Route des Molliats",
            "location": [
                6.15554,
                45.958821
            ]
        },
        {
            "hint": "GM88jCnRPIx5AAAAOAAAAGsAAABtAAAAhwEHQyASdkLxaOxCSKXyQnkAAAA4AAAAawAAAG0AAAC6AgEA2vBYAEB4ugIkFFkA7366AgcAjxW0OfpJ",
            "distance": 727.915402955,
            "name": "Route de l’Abbaye",
            "location": [
                5.828826,
                45.774912
            ]
        }
    ]
}
 */