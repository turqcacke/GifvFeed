package com.app.gifvfeed.presentation.ui.feed.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.app.gifvfeed.R
import com.app.gifvfeed.data.mappers.TimeLineItemMapper
import com.app.gifvfeed.data.network.MediaUrl
import com.app.gifvfeed.data.network.entity.EntryBlockBase
import com.app.gifvfeed.data.network.entity.TimeLineItemDto
import com.app.gifvfeed.data.network.responses.TimeLineResponse
import com.app.gifvfeed.data.network.serialization.EntryBlockDeserializer
import com.app.gifvfeed.data.network.serialization.TimeLineItemDtoDeserializer
import com.app.gifvfeed.domain.entity.EntryBlock
import com.app.gifvfeed.domain.entity.TimeLineItem
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.gson.GsonBuilder
import kotlin.math.abs


@Preview
@Composable
fun TimeLineCardListPreview() {
    val context = LocalContext.current
    val gson = GsonBuilder()
        .registerTypeAdapter(TimeLineItemDto::class.java, TimeLineItemDtoDeserializer())
        .registerTypeAdapter(
            EntryBlockBase::class.java, EntryBlockDeserializer()
        ).create()
    val jsonString = "{\n" +
            "    \"message\": \"\",\n" +
            "    \"result\": {\n" +
            "        \"items\": [\n" +
            "            {\n" +
            "                \"type\": \"entry\",\n" +
            "                \"data\": {\n" +
            "                    \"id\": 553556,\n" +
            "                    \"type\": 1,\n" +
            "                    \"author\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true\n" +
            "                    },\n" +
            "                    \"subsiteId\": 458157,\n" +
            "                    \"subsite\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true,\n" +
            "                        \"prevEntry\": null\n" +
            "                    },\n" +
            "                    \"title\": \"\",\n" +
            "                    \"date\": 1646390415,\n" +
            "                    \"blocks\": [\n" +
            "                        {\n" +
            "                            \"type\": \"media\",\n" +
            "                            \"data\": {\n" +
            "                                \"items\": [\n" +
            "                                    {\n" +
            "                                        \"title\": \"\",\n" +
            "                                        \"image\": {\n" +
            "                                            \"type\": \"image\",\n" +
            "                                            \"data\": {\n" +
            "                                                \"uuid\": \"e96e84ed-a74d-5404-8d9c-d33931fcc759\",\n" +
            "                                                \"width\": 289,\n" +
            "                                                \"height\": 300,\n" +
            "                                                \"size\": 27114,\n" +
            "                                                \"type\": \"jpg\",\n" +
            "                                                \"color\": \"919495\",\n" +
            "                                                \"hash\": \"\",\n" +
            "                                                \"external_service\": []\n" +
            "                                            }\n" +
            "                                        }\n" +
            "                                    }\n" +
            "                                ],\n" +
            "                                \"with_background\": false,\n" +
            "                                \"with_border\": false\n" +
            "                            },\n" +
            "                            \"cover\": true,\n" +
            "                            \"hidden\": false,\n" +
            "                            \"anchor\": \"\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"badges\": {\n" +
            "                        \"0\": {\n" +
            "                            \"type\": \"top\",\n" +
            "                            \"text\": \"Turquoise Cacke\",\n" +
            "                            \"background\": \"#ffffff\",\n" +
            "                            \"color\": \"#717173\",\n" +
            "                            \"border\": \"#ffffff\"\n" +
            "                        },\n" +
            "                        \"\\u0000*\\u0000items\": [],\n" +
            "                        \"\\u0000*\\u0000isEmptyValue\": []\n" +
            "                    },\n" +
            "                    \"counters\": {\n" +
            "                        \"comments\": 1,\n" +
            "                        \"favorites\": 0,\n" +
            "                        \"reposts\": 0\n" +
            "                    },\n" +
            "                    \"commentsSeenCount\": null,\n" +
            "                    \"dateFavorite\": 0,\n" +
            "                    \"hitsCount\": 73,\n" +
            "                    \"isCommentsEnabled\": true,\n" +
            "                    \"isLikesEnabled\": true,\n" +
            "                    \"isFavorited\": false,\n" +
            "                    \"isRepost\": false,\n" +
            "                    \"likes\": {\n" +
            "                        \"summ\": 3,\n" +
            "                        \"counter\": 3,\n" +
            "                        \"isLiked\": 0,\n" +
            "                        \"isHidden\": false\n" +
            "                    },\n" +
            "                    \"isPinned\": false,\n" +
            "                    \"canEdit\": false,\n" +
            "                    \"repost\": null,\n" +
            "                    \"isRepostedByMe\": false,\n" +
            "                    \"stackedRepostsAuthors\": [],\n" +
            "                    \"subscribedToTreads\": false,\n" +
            "                    \"isShowThanks\": false,\n" +
            "                    \"isStillUpdating\": false,\n" +
            "                    \"isFilledByEditors\": false,\n" +
            "                    \"isEditorial\": false,\n" +
            "                    \"isPromoted\": false,\n" +
            "                    \"audioUrl\": \"\",\n" +
            "                    \"commentEditor\": {\n" +
            "                        \"enabled\": true\n" +
            "                    },\n" +
            "                    \"coAuthor\": null,\n" +
            "                    \"isBlur\": false\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"entry\",\n" +
            "                \"data\": {\n" +
            "                    \"id\": 547045,\n" +
            "                    \"type\": 1,\n" +
            "                    \"author\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true,\n" +
            "                        \"prevEntry\": null\n" +
            "                    },\n" +
            "                    \"subsiteId\": 458157,\n" +
            "                    \"subsite\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true,\n" +
            "                        \"prevEntry\": null\n" +
            "                    },\n" +
            "                    \"title\": \"d\",\n" +
            "                    \"date\": 1645995660,\n" +
            "                    \"blocks\": [\n" +
            "                        {\n" +
            "                            \"type\": \"header\",\n" +
            "                            \"data\": {\n" +
            "                                \"text\": \"d\",\n" +
            "                                \"style\": \"h2\"\n" +
            "                            },\n" +
            "                            \"cover\": false,\n" +
            "                            \"hidden\": false,\n" +
            "                            \"anchor\": \"\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"type\": \"text\",\n" +
            "                            \"data\": {\n" +
            "                                \"text\": \"dddd\",\n" +
            "                                \"text_truncated\": \"<<<same>>>\"\n" +
            "                            },\n" +
            "                            \"cover\": false,\n" +
            "                            \"hidden\": false,\n" +
            "                            \"anchor\": \"\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"badges\": {\n" +
            "                        \"0\": {\n" +
            "                            \"type\": \"top\",\n" +
            "                            \"text\": \"Turquoise Cacke\",\n" +
            "                            \"background\": \"#ffffff\",\n" +
            "                            \"color\": \"#717173\",\n" +
            "                            \"border\": \"#ffffff\"\n" +
            "                        },\n" +
            "                        \"\\u0000*\\u0000items\": [],\n" +
            "                        \"\\u0000*\\u0000isEmptyValue\": []\n" +
            "                    },\n" +
            "                    \"counters\": {\n" +
            "                        \"comments\": 0,\n" +
            "                        \"favorites\": 0,\n" +
            "                        \"reposts\": 0\n" +
            "                    },\n" +
            "                    \"commentsSeenCount\": null,\n" +
            "                    \"dateFavorite\": 0,\n" +
            "                    \"hitsCount\": 24,\n" +
            "                    \"isCommentsEnabled\": true,\n" +
            "                    \"isLikesEnabled\": true,\n" +
            "                    \"isFavorited\": false,\n" +
            "                    \"isRepost\": false,\n" +
            "                    \"likes\": {\n" +
            "                        \"summ\": 0,\n" +
            "                        \"counter\": 0,\n" +
            "                        \"isLiked\": 0,\n" +
            "                        \"isHidden\": false\n" +
            "                    },\n" +
            "                    \"isPinned\": false,\n" +
            "                    \"canEdit\": false,\n" +
            "                    \"repost\": null,\n" +
            "                    \"isRepostedByMe\": false,\n" +
            "                    \"stackedRepostsAuthors\": [],\n" +
            "                    \"subscribedToTreads\": false,\n" +
            "                    \"isShowThanks\": false,\n" +
            "                    \"isStillUpdating\": false,\n" +
            "                    \"isFilledByEditors\": false,\n" +
            "                    \"isEditorial\": false,\n" +
            "                    \"isPromoted\": false,\n" +
            "                    \"audioUrl\": \"\",\n" +
            "                    \"commentEditor\": {\n" +
            "                        \"enabled\": true\n" +
            "                    },\n" +
            "                    \"coAuthor\": null,\n" +
            "                    \"isBlur\": false\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"entry\",\n" +
            "                \"data\": {\n" +
            "                    \"id\": 545133,\n" +
            "                    \"type\": 1,\n" +
            "                    \"author\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true,\n" +
            "                        \"prevEntry\": null\n" +
            "                    },\n" +
            "                    \"subsiteId\": 458157,\n" +
            "                    \"subsite\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true,\n" +
            "                        \"prevEntry\": null\n" +
            "                    },\n" +
            "                    \"title\": \"\",\n" +
            "                    \"date\": 1645896425,\n" +
            "                    \"blocks\": [\n" +
            "                        {\n" +
            "                            \"type\": \"media\",\n" +
            "                            \"data\": {\n" +
            "                                \"items\": [\n" +
            "                                    {\n" +
            "                                        \"title\": \"\",\n" +
            "                                        \"image\": {\n" +
            "                                            \"type\": \"image\",\n" +
            "                                            \"data\": {\n" +
            "                                                \"uuid\": \"8b2eb9c6-9d94-5be1-811d-8b0c5bfe6269\",\n" +
            "                                                \"width\": 220,\n" +
            "                                                \"height\": 220,\n" +
            "                                                \"size\": 279487,\n" +
            "                                                \"type\": \"gif\",\n" +
            "                                                \"color\": \"817066\",\n" +
            "                                                \"hash\": \"\",\n" +
            "                                                \"external_service\": []\n" +
            "                                            }\n" +
            "                                        }\n" +
            "                                    }\n" +
            "                                ],\n" +
            "                                \"with_background\": false,\n" +
            "                                \"with_border\": false\n" +
            "                            },\n" +
            "                            \"cover\": true,\n" +
            "                            \"hidden\": false,\n" +
            "                            \"anchor\": \"\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"type\": \"media\",\n" +
            "                            \"data\": {\n" +
            "                                \"items\": [\n" +
            "                                    {\n" +
            "                                        \"title\": \"\",\n" +
            "                                        \"image\": {\n" +
            "                                            \"type\": \"image\",\n" +
            "                                            \"data\": {\n" +
            "                                                \"uuid\": \"e96e84ed-a74d-5404-8d9c-d33931fcc759\",\n" +
            "                                                \"width\": 289,\n" +
            "                                                \"height\": 300,\n" +
            "                                                \"size\": 27114,\n" +
            "                                                \"type\": \"jpg\",\n" +
            "                                                \"color\": \"919495\",\n" +
            "                                                \"hash\": \"\",\n" +
            "                                                \"external_service\": []\n" +
            "                                            }\n" +
            "                                        }\n" +
            "                                    }\n" +
            "                                ],\n" +
            "                                \"with_background\": false,\n" +
            "                                \"with_border\": false\n" +
            "                            },\n" +
            "                            \"cover\": false,\n" +
            "                            \"hidden\": false,\n" +
            "                            \"anchor\": \"\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"badges\": {\n" +
            "                        \"0\": {\n" +
            "                            \"type\": \"top\",\n" +
            "                            \"text\": \"Turquoise Cacke\",\n" +
            "                            \"background\": \"#ffffff\",\n" +
            "                            \"color\": \"#717173\",\n" +
            "                            \"border\": \"#ffffff\"\n" +
            "                        },\n" +
            "                        \"\\u0000*\\u0000items\": [],\n" +
            "                        \"\\u0000*\\u0000isEmptyValue\": []\n" +
            "                    },\n" +
            "                    \"counters\": {\n" +
            "                        \"comments\": 0,\n" +
            "                        \"favorites\": 0,\n" +
            "                        \"reposts\": 0\n" +
            "                    },\n" +
            "                    \"commentsSeenCount\": null,\n" +
            "                    \"dateFavorite\": 0,\n" +
            "                    \"hitsCount\": 9,\n" +
            "                    \"isCommentsEnabled\": true,\n" +
            "                    \"isLikesEnabled\": true,\n" +
            "                    \"isFavorited\": false,\n" +
            "                    \"isRepost\": false,\n" +
            "                    \"likes\": {\n" +
            "                        \"summ\": 0,\n" +
            "                        \"counter\": 0,\n" +
            "                        \"isLiked\": 0,\n" +
            "                        \"isHidden\": false\n" +
            "                    },\n" +
            "                    \"isPinned\": false,\n" +
            "                    \"canEdit\": false,\n" +
            "                    \"repost\": null,\n" +
            "                    \"isRepostedByMe\": false,\n" +
            "                    \"stackedRepostsAuthors\": [],\n" +
            "                    \"subscribedToTreads\": false,\n" +
            "                    \"isShowThanks\": false,\n" +
            "                    \"isStillUpdating\": false,\n" +
            "                    \"isFilledByEditors\": false,\n" +
            "                    \"isEditorial\": false,\n" +
            "                    \"isPromoted\": false,\n" +
            "                    \"audioUrl\": \"\",\n" +
            "                    \"commentEditor\": {\n" +
            "                        \"enabled\": true\n" +
            "                    },\n" +
            "                    \"coAuthor\": null,\n" +
            "                    \"isBlur\": false\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"type\": \"entry\",\n" +
            "                \"data\": {\n" +
            "                    \"id\": 545110,\n" +
            "                    \"type\": 1,\n" +
            "                    \"author\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true,\n" +
            "                        \"prevEntry\": null\n" +
            "                    },\n" +
            "                    \"subsiteId\": 458157,\n" +
            "                    \"subsite\": {\n" +
            "                        \"id\": 458157,\n" +
            "                        \"type\": 1,\n" +
            "                        \"name\": \"Turquoise Cacke\",\n" +
            "                        \"description\": \"\",\n" +
            "                        \"avatar\": {\n" +
            "                            \"type\": \"image\",\n" +
            "                            \"data\": {\n" +
            "                                \"uuid\": \"4dbb6451-dd48-5458-a827-415826aa5b13\",\n" +
            "                                \"width\": 200,\n" +
            "                                \"height\": 200,\n" +
            "                                \"size\": 16396,\n" +
            "                                \"type\": \"jpg\",\n" +
            "                                \"color\": \"bcb9b4\",\n" +
            "                                \"hash\": \"\",\n" +
            "                                \"external_service\": []\n" +
            "                            }\n" +
            "                        },\n" +
            "                        \"cover\": null,\n" +
            "                        \"isSubscribed\": false,\n" +
            "                        \"isVerified\": false,\n" +
            "                        \"isOnline\": false,\n" +
            "                        \"isMuted\": false,\n" +
            "                        \"isUnsubscribable\": true,\n" +
            "                        \"isAvailableForMessenger\": true,\n" +
            "                        \"prevEntry\": null\n" +
            "                    },\n" +
            "                    \"title\": \"\",\n" +
            "                    \"date\": 1645895816,\n" +
            "                    \"blocks\": [\n" +
            "                        {\n" +
            "                            \"type\": \"instagram\",\n" +
            "                            \"data\": {\n" +
            "                                \"instagram\": {\n" +
            "                                    \"type\": \"universalbox\",\n" +
            "                                    \"data\": {\n" +
            "                                        \"service\": \"instagram\",\n" +
            "                                        \"box_data\": {\n" +
            "                                            \"url\": \"https://instagram.com/p/CabqEtJI4Fx\",\n" +
            "                                            \"image\": null,\n" +
            "                                            \"title\": null,\n" +
            "                                            \"version\": 4\n" +
            "                                        }\n" +
            "                                    }\n" +
            "                                },\n" +
            "                                \"title\": \"\",\n" +
            "                                \"hide_title\": true,\n" +
            "                                \"markdown\": \"\"\n" +
            "                            },\n" +
            "                            \"cover\": true,\n" +
            "                            \"hidden\": false,\n" +
            "                            \"anchor\": \"\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"badges\": {\n" +
            "                        \"0\": {\n" +
            "                            \"type\": \"top\",\n" +
            "                            \"text\": \"Turquoise Cacke\",\n" +
            "                            \"background\": \"#ffffff\",\n" +
            "                            \"color\": \"#717173\",\n" +
            "                            \"border\": \"#ffffff\"\n" +
            "                        },\n" +
            "                        \"\\u0000*\\u0000items\": [],\n" +
            "                        \"\\u0000*\\u0000isEmptyValue\": []\n" +
            "                    },\n" +
            "                    \"counters\": {\n" +
            "                        \"comments\": 0,\n" +
            "                        \"favorites\": 0,\n" +
            "                        \"reposts\": 0\n" +
            "                    },\n" +
            "                    \"commentsSeenCount\": null,\n" +
            "                    \"dateFavorite\": 0,\n" +
            "                    \"hitsCount\": 7,\n" +
            "                    \"isCommentsEnabled\": true,\n" +
            "                    \"isLikesEnabled\": true,\n" +
            "                    \"isFavorited\": false,\n" +
            "                    \"isRepost\": false,\n" +
            "                    \"likes\": {\n" +
            "                        \"summ\": 7,\n" +
            "                        \"counter\": 7,\n" +
            "                        \"isLiked\": 0,\n" +
            "                        \"isHidden\": false\n" +
            "                    },\n" +
            "                    \"isPinned\": false,\n" +
            "                    \"canEdit\": false,\n" +
            "                    \"repost\": null,\n" +
            "                    \"isRepostedByMe\": false,\n" +
            "                    \"stackedRepostsAuthors\": [],\n" +
            "                    \"subscribedToTreads\": false,\n" +
            "                    \"isShowThanks\": false,\n" +
            "                    \"isStillUpdating\": false,\n" +
            "                    \"isFilledByEditors\": false,\n" +
            "                    \"isEditorial\": false,\n" +
            "                    \"isPromoted\": false,\n" +
            "                    \"audioUrl\": \"\",\n" +
            "                    \"commentEditor\": {\n" +
            "                        \"enabled\": true\n" +
            "                    },\n" +
            "                    \"coAuthor\": null,\n" +
            "                    \"isBlur\": false\n" +
            "                }\n" +
            "            }\n" +
            "        ],\n" +
            "        \"lastId\": 545110,\n" +
            "        \"lastSortingValue\": 1645895816\n" +
            "    }\n" +
            "}"
    val response = gson.fromJson(jsonString, TimeLineResponse::class.java)
    val timeLineItems = TimeLineItemMapper().toListedEntity(response.result.items)
    if (timeLineItems.isNotEmpty()) {
        TimelineCardList(
            modifier = Modifier.fillMaxSize(),
            list = timeLineItems
        ) {
            Log.d("LOAD_MORE", "init")
            Toast.makeText(
                context,
                "Load more",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Composable
fun TimelineCardList(
    modifier: Modifier = Modifier,
    list: List<TimeLineItem>,
    loadMoreState: State<Boolean>? = null,
    loadMore: () -> Unit = {}
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }

    val loadingMore = remember {
        when (loadMoreState) {
            null -> mutableStateOf(false)
            else -> loadMoreState

        }
    }

    var playingItem by remember {
        mutableStateOf(
            list.firstOrNull()?.let {
                when (isMediaPlayable(it)) {
                    true -> return@let it
                    else -> return@let null
                }
            }
        )
    }

    LaunchedEffect(playingItem) {
        if (playingItem == null) {
            exoPlayer.pause()
        } else {
            val playingEntry =
                (playingItem as TimeLineItem.TimeLineEntry).data.mediaBlocks.firstOrNull()
            exoPlayer.setMediaItem(MediaItem.fromUri(MediaUrl.getVideoUrl(playingEntry!!.data.uuid)))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        }
    }

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        snapshotFlow {
            listState.playingItem(list)
        }.collect { videoItem ->
            playingItem = videoItem
        }
    }

    DisposableEffect(exoPlayer) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (playingItem == null) return@LifecycleEventObserver
            when (event) {
                Lifecycle.Event.ON_START -> exoPlayer.play()
                Lifecycle.Event.ON_STOP -> exoPlayer.pause()
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            exoPlayer.release()
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(list.size) {
            TimeLineCard(
                timeLineItem = list[it],
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                exoPlayer = exoPlayer,
                isPlaying = list.indexOf(playingItem) == it
            )
        }
        if (loadingMore.value) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(id = R.dimen.dimen_default_16),
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    CircularProgressIndicator()
                }
            }
        }
    }

    listState.OnBottomReached {
        loadMore()
    }
}

@Composable
private fun LazyListState.OnBottomReached(
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastListItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true
            lastListItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) {
                    loadMore()
                }
            }
    }
}

private fun LazyListState.playingItem(
    timeLineItemList: List<TimeLineItem>
): TimeLineItem? {
    if (layoutInfo.visibleItemsInfo.isNullOrEmpty() || timeLineItemList.isEmpty()) return null
    val layoutInfo = layoutInfo
    val visibleItems = layoutInfo.visibleItemsInfo
    val lastItem = visibleItems.last()

    val firstItemVisible = when {
        firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset == 0 -> {
            isVisibleByIndex(timeLineItemList, 0)
        }
        else -> false
    }

    val itemSize = lastItem.size
    val itemOffset = lastItem.offset
    val totalOffset = layoutInfo.viewportEndOffset

    val lastItemVisible = when {
        lastItem.index == timeLineItemList.size - 1 && totalOffset - itemOffset >= itemSize -> isVisibleByIndex(
            timeLineItemList,
            timeLineItemList.lastIndex
        )
        else -> false
    }

    val midPoint =when{
        visibleItems.size % 2 == 0 -> {
            visibleItems.size / 2
        }
        else -> (visibleItems.size / 2) + 1
    }

    val centerItems = visibleItems.sortedBy {
        abs(midPoint - (visibleItems.indexOf(it) + 1))
    }

    return when {
        firstItemVisible -> timeLineItemList.first()
        lastItemVisible -> timeLineItemList.last()
        else -> {
            for (info in centerItems) {
                val item = timeLineItemList[info.index]
                if (isMediaPlayable(item)) {
                    return timeLineItemList[info.index]
                }
            }
            return null
        }
    }
}

fun isMediaPlayable(item: TimeLineItem): Boolean {
    val firstItem = when (item) {
        is TimeLineItem.TimeLineEntry -> {
            item.data.mediaBlocks.firstOrNull()
        }
        else -> {
            null
        }
    }
    return firstItem is EntryBlock.MediaBlock && firstItem.data.isPlayable
}

fun isVisibleByIndex(timeLineItemList: List<TimeLineItem>, index: Int): Boolean {
    return when (val entryItem = timeLineItemList.getOrNull(index)) {
        is TimeLineItem.TimeLineEntry -> {
            val mediaEntry = entryItem.data.mediaBlocks.firstOrNull()
            mediaEntry is EntryBlock.MediaBlock && mediaEntry.data.isPlayable
        }
        else -> {
            false
        }
    }
}
