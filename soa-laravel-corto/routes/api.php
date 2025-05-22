<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\StudentControllerAPI;

Route::get('/', function () {
    return response()->json(['message' => 'API is working!']);
});

Route::prefix('students')->controller(StudentControllerAPI::class)->group(function () {
    Route::get('/', 'index')->name('students.index');
    Route::get('/id/{id}', 'showById');           
    Route::get('{cedula}', 'showByCedula');         
    Route::post('/', 'store');              
    Route::put('id/{id}', 'update');       
    Route::delete('id/{id}', 'destroy');   
});

Route::fallback(function () {
    return response()->json(['message' => 'Not Found'], 404);
});

