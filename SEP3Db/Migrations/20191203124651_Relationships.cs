using Microsoft.EntityFrameworkCore.Migrations;

namespace SEP3Db.Migrations
{
    public partial class Relationships : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "PersonId",
                table: "Reservations");

            migrationBuilder.DropColumn(
                name: "UserId",
                table: "Borrowed");

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Reservations",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "integer");

            migrationBuilder.AddColumn<string>(
                name: "Username",
                table: "Reservations",
                nullable: true);

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Borrowed",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "integer");

            migrationBuilder.AddColumn<string>(
                name: "Username",
                table: "Borrowed",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_Reservations_ItemId",
                table: "Reservations",
                column: "ItemId");

            migrationBuilder.CreateIndex(
                name: "IX_Reservations_Username",
                table: "Reservations",
                column: "Username");

            migrationBuilder.CreateIndex(
                name: "IX_Borrowed_ItemId",
                table: "Borrowed",
                column: "ItemId");

            migrationBuilder.CreateIndex(
                name: "IX_Borrowed_Username",
                table: "Borrowed",
                column: "Username");

            migrationBuilder.AddForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Borrowed_Users_Username",
                table: "Borrowed",
                column: "Username",
                principalTable: "Users",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Reservations_Items_ItemId",
                table: "Reservations",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Restrict);

            migrationBuilder.AddForeignKey(
                name: "FK_Reservations_Users_Username",
                table: "Reservations",
                column: "Username",
                principalTable: "Users",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Borrowed_Items_ItemId",
                table: "Borrowed");

            migrationBuilder.DropForeignKey(
                name: "FK_Borrowed_Users_Username",
                table: "Borrowed");

            migrationBuilder.DropForeignKey(
                name: "FK_Reservations_Items_ItemId",
                table: "Reservations");

            migrationBuilder.DropForeignKey(
                name: "FK_Reservations_Users_Username",
                table: "Reservations");

            migrationBuilder.DropIndex(
                name: "IX_Reservations_ItemId",
                table: "Reservations");

            migrationBuilder.DropIndex(
                name: "IX_Reservations_Username",
                table: "Reservations");

            migrationBuilder.DropIndex(
                name: "IX_Borrowed_ItemId",
                table: "Borrowed");

            migrationBuilder.DropIndex(
                name: "IX_Borrowed_Username",
                table: "Borrowed");

            migrationBuilder.DropColumn(
                name: "Username",
                table: "Reservations");

            migrationBuilder.DropColumn(
                name: "Username",
                table: "Borrowed");

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Reservations",
                type: "integer",
                nullable: false,
                oldClrType: typeof(int),
                oldNullable: true);

            migrationBuilder.AddColumn<int>(
                name: "PersonId",
                table: "Reservations",
                type: "integer",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AlterColumn<int>(
                name: "ItemId",
                table: "Borrowed",
                type: "integer",
                nullable: false,
                oldClrType: typeof(int),
                oldNullable: true);

            migrationBuilder.AddColumn<int>(
                name: "UserId",
                table: "Borrowed",
                type: "integer",
                nullable: false,
                defaultValue: 0);
        }
    }
}
