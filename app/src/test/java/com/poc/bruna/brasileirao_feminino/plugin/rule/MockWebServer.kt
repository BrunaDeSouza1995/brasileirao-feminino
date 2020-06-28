package com.poc.bruna.brasileirao_feminino.plugin.rule

import com.poc.bruna.brasileirao_feminino.plugin.dagger.module.RetrofitModule
import com.poc.bruna.brasileirao_feminino.plugin.retrofit.Service
import com.poc.bruna.brasileirao_feminino.plugin.utils.FileLoaderUtils.getResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_OK

class MockWebServerRule : TestRule {

    lateinit var server: MockWebServer
    lateinit var service: Service

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                startServer()
                try {
                    base?.evaluate()
                } finally {
                    stopServer()
                }
            }
        }
    }

    fun startServer() {
        server = MockWebServer()
        server.apply {
            dispatcher = getDispatcher()
            start(MOCK_WEB_SERVER_PORT)
        }
        service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(RetrofitModule().getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }

    private fun getDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    GAME_PATH_FOUND -> {
                        MockResponse()
                            .setResponseCode(HTTP_OK)
                            .setBody(getResponse(this::class.java, GAME_PATH_FOUND_JSON))
                    }
                    GAME_PATH_NOT_FOUND -> {
                        MockResponse()
                            .setResponseCode(HTTP_OK)
                            .setBody(getResponse(this::class.java, GAME_PATH_NOT_FOUND_JSON))
                    }
                    else -> {
                        MockResponse()
                            .setResponseCode(HTTP_NOT_FOUND)
                    }
                }
            }
        }
    }

    fun stopServer() {
        server.shutdown()
    }

    companion object {
        private const val MOCK_WEB_SERVER_PORT = 8000
        private const val GAME_PATH_FOUND_JSON = "json/game_found.json"
        private const val GAME_PATH_NOT_FOUND_JSON = "json/game_not_found.json"
        private const val GAME_PATH_NOT_FOUND =
            "/tabela/56a50da4-cad0-4cf2-9633-661d598b7cfb/fase/primeira-fase-brasileiro-feminino-2020/rodada/0/jogos/"
        private const val GAME_PATH_FOUND =
            "/tabela/56a50da4-cad0-4cf2-9633-661d598b7cfb/fase/primeira-fase-brasileiro-feminino-2020/rodada/1/jogos/"
    }
}