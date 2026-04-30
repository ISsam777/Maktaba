package com.ElOuedUniv.maktaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan_tennert.supabase.SupabaseClient
import io.github.jan_tennert.supabase.createSupabaseClient
import io.github.jan_tennert.supabase.postgrest.Postgrest
import io.github.jan_tennert.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://jfeyqaseegnnxxmxunkt.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpmZXlxYXNlZWdubnh4bXh1bmt0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzc1NDU0OTEsImV4cCI6MjA5MzEyMTQ5MX0.2XqCqaGL_cA1UENr95427RzE0zFPtzCL-tYgoj2-F28"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
}
