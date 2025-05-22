<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;
use Illuminate\Support\Facades\DB;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('students', function (Blueprint $table) {
            $table->id();
            $table->string('cedula', 10)->unique();
            $table->string('name', 100);
            $table->string('lastname', 100);
            $table->string('address', 100);
            $table->string('telephone', 10);
            $table->timestamps();
            $table->softDeletes();
        });
        
        DB::statement('ALTER TABLE students AUTO_INCREMENT = 1000');
    }

    public function down(): void
    {
        Schema::dropIfExists('students');
    }
};
