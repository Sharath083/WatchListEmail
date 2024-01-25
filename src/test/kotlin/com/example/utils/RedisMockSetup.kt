import com.example.redis.RedisPool
import io.mockk.every
import io.mockk.mockk

object RedisMockSetup {
    private lateinit var redisPoolMock: RedisPool
    fun createRedisMock() {

        redisPoolMock = mockk<RedisPool>(relaxed = true)
        every {
            redisPoolMock.resource.get("6e4db4d9415bedcf6dd63b9d3aa5e1f2")
        } returns "accountId=%23s6d4d3db8-2241-42b2-b019-4c6e31d705ba"
    }

}



