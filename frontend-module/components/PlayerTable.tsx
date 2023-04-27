import { gameTypeToPrintableName } from '@/util/GameType';
import { Player } from '@/util/interfaces/Player';
import { Table } from 'antd';
import Link from 'next/link';

export const PlayerTable = ({ players, isAll, isLoading }: { players: Player[]; isAll: boolean; isLoading: boolean }) => {
  return (
    <Table dataSource={players} size="small" bordered pagination={false} scroll={{ y: 600 }} style={{ minHeight: 600 }} loading={isLoading}>
      <Table.Column title="ID" width="15%" render={(_, record: Player) => record.player_id} sorter={(a, b) => a.player_id.localeCompare(b.player_id)} />
      <Table.Column
        title="Name"
        render={(_, record: Player) => <Link href={`/player/${record.player_id}`}>{record.name}</Link>}
        sorter={(a, b) => a.name.localeCompare(b.name)}
      />
      {isAll && (
        <Table.Column
          title="Spiel"
          render={(_, record: Player) => gameTypeToPrintableName(record.game_type)}
          sorter={(a, b) => a.game_type.localeCompare(b.game_type)}
        />
      )}
      <Table.Column title="KDA" render={(_, record: Player) => record.global_kda.toFixed(2)} sorter={(a, b) => a.global_kda - b.global_kda} />
    </Table>
  );
};
