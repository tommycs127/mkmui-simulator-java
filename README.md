# MK 妹機械人 (Java 版)
模仿香港 VTuber「[MK 妹](https://www.youtube.com/channel/UCO62chyehk6pX7OitrnJAUg)」講嘢風格嘅 Spigot / Paper 外掛，有得同玩家傾偈之餘，重保留咗連 Discord 嘅功能添！
入面嘅 `FakeMKMui.java`、`Sentence.java` 同 `Name.java` 都係 MK 妹機械人嘅靈魂，想用喺自己嘅 Java 專案嘅話就要拎晒呢三件。

# 適用伺服器版本
* `1.19.2` <sub>懶得研究點樣跨版本，麻煩自己砌返對應版本嘅 API 再編譯</sub>

# 指令
| 指令 | 功能 | 權限 | 備註 |
| --- | --- | --- | --- |
| `/mkmui say <句話>` | 同 MK 妹講嘢 | `mkmui.say` | `<句話>` 空得，但係 MK 妹大概會問候你 |
| `/mkmui version` | 睇外掛作者同版本 | 無 | 無 |
| `/mkmui reload` | 重讀 `config.yml` | `mkmui.reload` | 無 |


# 點用佢啊？
1. 擺 `MKMuiSimulator.jar` 落 `/plugins` 入面；
2. 開伺服器；
3. 攪掂。夠鐘又收工。

# 點連到 Discord 啊？
1. 去 [Discord 開發者介面](https://discord.com/developers/applications/)開一個新 bot；
2. 去 `Bot` 分頁複製 token 再貼落去 `MKMuiSimulator/config.yml` 嘅 `discord-token:` 隔籬；
   * 譖氣都要講：*唔好畀人知你個 token！*
3. 去 `OAuth2` 分頁嘅 `URL Generator` 整個邀請連結；
   * `SCOPES` 剔 `bot`
   * `BOT PERMISSIONS` 剔 `Send Messages`
4. 貼落瀏覽器，揀你想佢入嘅伺服器；
5. 喺 Minecraft 伺服器度打 `/mkmui reload`；
6. 見到 `[net.dv8tion.jda.api.JDA] Finished Loading!` 即係程式正常運作緊：
7. **娘娘駕到！**

# 點樣喺 Discord 同娘娘傾計啊？
喺訊息開頭打呢啲觸發詞：
* `$娘娘`
* `$MK妹`；或者
* `$mk妹`

佢就會~~鬧你~~覆你。

留意觸發詞前面*唔應該有任何字符（包括空格），亦唔好係全形嘅* `＄`，唔係嘅話佢連理都唔會理。

如果你有需要喺訊息開頭打觸發詞，但係又唔想佢應你，就要喺 `$` 前面加個反斜線變成 `\$`。

# 編譯依賴套裝
* [JDA](https://github.com/DV8FromTheWorld/JDA)
* [Spigot API](https://www.spigotmc.org/wiki/buildtools/)（你要自己用 `BuildTools.jar` 砌出嚟；版本自己揀）

# 你（外掛）有咩問題？
* 事關靠 JDA 連 Discord，內部運作會同 [DiscordSRV](https://github.com/DiscordSRV/DiscordSRV) 撞咗線，不過未睇到會影響兩邊功能。
  * 暫時唔太知要點樣解。
