using Microsoft.EntityFrameworkCore.Migrations;

namespace SEP3Db.Migrations
{
    public partial class Relationships2 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed");

            migrationBuilder.DropForeignKey(
                name: "FK_Reservations_Items_ItemId",
                table: "Reservations");

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Reservations",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "integer",
                oldNullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Borrowed",
                nullable: false,
                oldClrType: typeof(int),
                oldType: "integer",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Reservations_Items_ItemId",
                table: "Reservations",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed");

            migrationBuilder.DropForeignKey(
                name: "FK_Reservations_Items_ItemId",
                table: "Reservations");

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Reservations",
                type: "integer",
                nullable: true,
                oldClrType: typeof(int));

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Borrowed",
                type: "integer",
                nullable: true,
                oldClrType: typeof(int));

            migrationBuilder.AddForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Reservations_Items_ItemId",
                table: "Reservations",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
