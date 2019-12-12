using Microsoft.EntityFrameworkCore.Migrations;

namespace SEP3Db.Migrations
{
    public partial class borrowedboolisreturned : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
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
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_Reservations_ItemId",
                table: "Reservations",
                column: "ItemId");

            migrationBuilder.CreateIndex(
                name: "IX_Reservations_Username",
                table: "Reservations",
                column: "Username");

            migrationBuilder.AddForeignKey(
                name: "FK_Reservations_Items_ItemId",
                table: "Reservations",
                column: "ItemId",
                principalTable: "Items",
                principalColumn: "ItemId",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_Reservations_Users_Username",
                table: "Reservations",
                column: "Username",
                principalTable: "Users",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
